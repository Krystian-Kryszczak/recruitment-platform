package krystian.kryszczak.recruitment.service.security.password

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.reset.ResetPasswordRepository
import krystian.kryszczak.recruitment.factory.password.ResetPasswordFactory
import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.validation.PasswordValidator
import krystian.kryszczak.recruitment.service.AbstractDataAccessService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.credentials.being.BeingCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.candidate.CandidateCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.employer.EmployerCredentialsService
import reactor.core.publisher.Mono

@Singleton
class DefaultResetPasswordService(
    private val employerCredentialsService: EmployerCredentialsService,
    private val candidateCredentialsService: CandidateCredentialsService,
    private val resetPasswordFactory: ResetPasswordFactory,
    private val passwordEncoder: PasswordEncoder,
    private val smtpMailerService: SmtpMailerService,
    override val repository: ResetPasswordRepository
) : ResetPasswordService, AbstractDataAccessService<ResetPassword, String>(repository) {
    override fun generateResetPasswordCode(accountType: AccountType, id: String, oldPassword: String) =
        generateChangePasswordCode(accountType, id, oldPassword, selectCredentialsService(accountType))

    private fun generateChangePasswordCode(accountType: AccountType, id: String, oldPassword: String,
                                           credentialsService: BeingCredentialsService<*>): Mono<ResetPassword> =
        if (PasswordValidator.validate(oldPassword)) {
            credentialsService.findById(id).flatMap { credentials ->
                Mono.justOrEmpty<String>(credentials.hashedPassword)
                    .filter { passwordEncoder.matches(oldPassword, it) }
                    .map { resetPasswordFactory.createWithGeneratedCode(id, accountType) }
            }
        } else {
            Mono.empty()
        }

    override fun sendResetPasswordCodeToEmail(resetPassword: ResetPassword, email: String): Mono<Boolean> =
        Mono.fromCallable { smtpMailerService.sendResetPasswordCode(email, resetPassword.code) }
            .thenReturn(true)
            .onErrorReturn(false)

    override fun finishResetPasswordProcess(accountType: AccountType, resetCode: String, newPassword: String): Mono<Boolean> =
        finishResetPasswordProcess(accountType, resetCode, newPassword, selectCredentialsService(accountType))

    private fun finishResetPasswordProcess(accountType: AccountType, resetCode: String, newPassword: String, credentialsService: BeingCredentialsService<*>): Mono<Boolean> {
        return if (resetCode.isNotBlank() || PasswordValidator.validate(newPassword)) {
            Mono.from(repository.findByAccountTypeAndCode(accountType, resetCode))
                .flatMap { resetPassword ->
                    credentialsService.updatePasswordById(resetPassword.targetId, newPassword)
                        .flatMap { repository.delete(resetPassword) }
                        .hasElement()
                }.defaultIfEmpty(false)
        } else {
            Mono.just(false)
        }
    }

    private fun selectCredentialsService(accountType: AccountType) =
        if (accountType == AccountType.EMPLOYER) employerCredentialsService
        else candidateCredentialsService
}
