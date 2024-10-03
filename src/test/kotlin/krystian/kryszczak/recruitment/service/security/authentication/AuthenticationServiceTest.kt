package krystian.kryszczak.recruitment.service.security.authentication

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.constant.AccountType.*
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.security.credentials.being.candidate.CandidateCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.employer.EmployerCredentialsService
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class AuthenticationServiceTest(authenticationService: AuthenticationService) : FreeSpec({
    "authentication service test" - {
        "authenticate" {
            val given = listOf(
                Triple(
                    HttpRequest.POST("/login", "").header("Account-Type", "employer"),
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.success("john.smith@gmail.com", listOf(EMPLOYER.name), mapOf("ID" to "<employer-id>"))
                ), Triple(
                    HttpRequest.POST("/login", "").header("Account-Type", "employer"),
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.success("john.smith@gmail.com", listOf(EMPLOYER.name), mapOf("ID" to "<employer-id>"))
                ),

                Triple(
                    HttpRequest.POST("/login", "").header("Account-Type", "candidate"),
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.success("john.smith@gmail.com", listOf(CANDIDATE.name), mapOf("ID" to "<candidate-id>"))
                ), Triple(
                    HttpRequest.POST("/login", "").header("Account-Type", "candidate"),
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.success("john.smith@gmail.com", listOf(CANDIDATE.name), mapOf("ID" to "<candidate-id>"))
                ), Triple(
                    HttpRequest.POST("/login", "").header("Account-Type", "candidate"),
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.success("john.smith@gmail.com", listOf(CANDIDATE.name), mapOf("ID" to "<candidate-id>"))
                ),

                Triple(
                    HttpRequest.POST("/login", ""),
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.failure()
                ), Triple(
                    null,
                    UsernamePasswordCredentials("john.smith@gmail.com", "raw-password"),
                    AuthenticationResponse.failure()
                )
            )

            // given
            for ((context, request, excepted) in given) {
                // when
                val result = Mono.from(authenticationService.authenticate(context, request))
                    .block()

                // then
                result.shouldNotBeNull().run {
                    isAuthenticated shouldBe excepted.isAuthenticated
                    excepted.authentication.ifPresent { excepted ->
                        authentication.shouldBePresent {
                            name shouldBe excepted.name
                            roles shouldBe excepted.roles
                            attributes shouldBe excepted.attributes
                        }
                    }
                    message shouldBe excepted.message
                }
            }
        }
    }
}) {
    @MockBean(EmployerCredentialsService::class)
    fun employerCredentialsService(): EmployerCredentialsService {
        return mockk<EmployerCredentialsService> {
            every { findByUsername(any()) } returns Mono.just(
                EmployerCredentials(null, "john.smith@gmail.com", "encoded-password")
            )
        }
    }

    @MockBean(CandidateCredentialsService::class)
    fun candidateCredentialsService(): CandidateCredentialsService {
        return mockk<CandidateCredentialsService> {
            every { findByUsername(any()) } returns Mono.just(
                CandidateCredentials(null, "john.smith@gmail.com", "encoded-password")
            )
        }
    }

    @MockBean(EmployerService::class)
    fun employerService(): EmployerService {
        return mockk<EmployerService> {
            every { findByEmail(any()) } returns Mono.just(
                Employer("<employer-id>", "John", country = "USA")
            )
        }
    }

    @MockBean(CandidateService::class)
    fun candidateService(): CandidateService {
        return mockk<CandidateService> {
            every { findByEmail(any()) } returns Mono.just(
                Candidate("<candidate-id>", "john.smith@gmail.com", "John", "Smith")
            )
        }
    }

    @MockBean(PasswordEncoder::class)
    fun passwordEncoder(): PasswordEncoder {
        return mockk<PasswordEncoder> {
            every { encode("raw-password") } returns "encoded-password"
            every { matches("raw-password", "encoded-password") } returns true
        }
    }
}
