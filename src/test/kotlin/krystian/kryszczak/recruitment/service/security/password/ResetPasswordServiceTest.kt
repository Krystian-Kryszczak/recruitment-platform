package krystian.kryszczak.recruitment.service.security.password

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.reset.ResetPasswordRepository
import krystian.kryszczak.recruitment.factory.password.ResetPasswordFactory
import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.validation.PasswordValidator
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.credentials.being.BeingCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.candidate.CandidateCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.employer.EmployerCredentialsService
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class ResetPasswordServiceTest(resetPasswordService: ResetPasswordService) : FreeSpec({
    "reset password service test" - {
        "generate reset password code" {
            // given
            val given = listOf(
                AccountType.EMPLOYER to Triple(
                    "<employer-id>",
                    "!Password!123456",
                    ResetPassword(
                        null, "<target-id>", "<reset-code>", AccountType.EMPLOYER, null
                    )
                ),
                AccountType.CANDIDATE to Triple(
                    "<candidate-id>",
                    "!Password!123456",
                    ResetPassword(
                        null, "<target-id>", "<reset-code>", AccountType.CANDIDATE, null
                    )
                )
            )

            for ((accountType, data) in given) {
                // given
                val (id, oldPassword, excepted) = data

                // when
                val result = resetPasswordService.generateResetPasswordCode(accountType, id, oldPassword)
                    .block()

                // then
                PasswordValidator.validate(oldPassword).shouldBeTrue()
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }

        "send change user password code to email" {
            // given
            val given = listOf(
                ResetPassword(null, "<target-id>", "<code>", AccountType.EMPLOYER) to "john@smith.net",
                ResetPassword(null, "<target-id>", "<code>", AccountType.CANDIDATE) to "eliot@alderson.net"
            )

            for ((resetPassword, email) in given) {
                // when
                val result = resetPasswordService.sendResetPasswordCodeToEmail(resetPassword, email)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBeTrue()
            }
        }

        "finish reset password process" {
            // given
            val given = listOf(
                Triple(
                    AccountType.EMPLOYER,
                    "<reset-code>",
                    "<new-employer-password>"
                ),
                Triple(
                    AccountType.CANDIDATE,
                    "<reset-code>",
                    "<new-candidate-password>"
                )
            )

            for ((accountType, resetCode, newPassword) in given) {
                // when
                val result = resetPasswordService.finishResetPasswordProcess(accountType, resetCode, newPassword)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBeTrue()
            }
        }
    }
}) {
    private inline fun <C : BeingCredentials, reified S : BeingCredentialsService<C>> credentialsService(result: (String?, String, String) -> C): S {
        return mockk<S> {
            every { findById(any()) } returns Mono.just(result(null, "john@smith.com", "<encoded-password>"))
            every { updatePasswordById(any(), any()) } returns Mono.just(Long.MAX_VALUE)
        }
    }

    @MockBean(EmployerCredentialsService::class)
    fun employerCredentialsService() = credentialsService<EmployerCredentials, EmployerCredentialsService>(::EmployerCredentials)

    @MockBean(CandidateCredentialsService::class)
    fun candidateCredentialsService() = credentialsService<CandidateCredentials, CandidateCredentialsService>(::CandidateCredentials)

    @MockBean(ResetPasswordFactory::class)
    fun resetPasswordFactory(): ResetPasswordFactory {
        return mockk<ResetPasswordFactory> {
            every { createWithGeneratedCode(any(), AccountType.EMPLOYER) } returns ResetPassword(
                null, "<target-id>", "<reset-code>", AccountType.EMPLOYER, null
            )
            every { createWithGeneratedCode(any(), AccountType.CANDIDATE) } returns ResetPassword(
                null, "<target-id>", "<reset-code>", AccountType.CANDIDATE, null
            )
        }
    }

    @MockBean(PasswordEncoder::class)
    fun passwordEncoder(): PasswordEncoder {
        return mockk<PasswordEncoder> {
            every { matches("!Password!123456", "<encoded-password>") } returns true
        }
    }

    @MockBean(SmtpMailerService::class)
    fun smtpMailerService(): SmtpMailerService {
        return mockk<SmtpMailerService> {
            every { sendResetPasswordCode(any(), any()) } returns Unit
        }
    }

    @MockBean(ResetPasswordRepository::class)
    fun resetPasswordRepository(): ResetPasswordRepository {
        return mockk<ResetPasswordRepository> {
            every { findByAccountTypeAndCode(AccountType.EMPLOYER, any()) } returns Mono.just(
                ResetPassword(null, "<reset-password-id>", "<reset-code>", AccountType.EMPLOYER)
            )
            every { findByAccountTypeAndCode(AccountType.CANDIDATE, any()) } returns Mono.just(
                ResetPassword(null, "<reset-password-id>", "<reset-code>", AccountType.CANDIDATE)
            )
            every { delete(any()) } returns Mono.just(Long.MAX_VALUE)
        }
    }
}
