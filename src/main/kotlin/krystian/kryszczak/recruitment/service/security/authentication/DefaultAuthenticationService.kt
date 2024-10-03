package krystian.kryszczak.recruitment.service.security.authentication

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.extension.authentication.*
import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.service.being.BeingService
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.security.credentials.being.BeingCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.candidate.CandidateCredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.being.employer.EmployerCredentialsService
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

@Singleton
class DefaultAuthenticationService(
    private val employerCredentialsService: EmployerCredentialsService,
    private val candidateCredentialsService: CandidateCredentialsService,
    private val employerService: EmployerService,
    private val candidateService: CandidateService,
    private val passwordEncoder: PasswordEncoder
): AuthenticationService {
    override fun authenticate(context: HttpRequest<*>?, request: AuthenticationRequest<*, *>): Mono<AuthenticationResponse> =
        if (context.accountTypeEquals(AccountType.EMPLOYER)) {
            authenticate(employerCredentialsService, employerService, request, AccountType.EMPLOYER)
        } else if (context.accountTypeEquals(AccountType.CANDIDATE)) {
            authenticate(candidateCredentialsService, candidateService, request, AccountType.CANDIDATE)
        } else {
            Mono.just(AuthenticationResponse.failure())
        }

    private fun HttpRequest<*>?.accountTypeEquals(accountType: AccountType) =
        this?.let { headers.get("Account-Type")?.equals(accountType.name, true) } ?: false

    private fun <T : BeingCredentials> authenticate(
        credentialsService: BeingCredentialsService<T>,
        beingService: BeingService<*, *, *>,
        request: AuthenticationRequest<*, *>,
        role: AccountType
    ): Mono<AuthenticationResponse> = request.let { (login, password) ->
        credentialsService.findByUsername(login)
            .mapNotNull<String>(BeingCredentials::hashedPassword)
            .filter { passwordEncoder.matches(password, it) }
            .flatMap { beingService.findByEmail(login) }
            .map { AuthenticationResponse.success(login, listOf(role.name), mapOf("ID" to it.id)) }
            .doOnNext { logger.info("User (${role.name}) with email address $login login successfully logged in.") }
            .defaultIfEmpty(AuthenticationResponse.failure(CREDENTIALS_DO_NOT_MATCH))
    }

    companion object {
        @JvmStatic
        private val logger = LoggerFactory.getLogger(DefaultAuthenticationService::class.java)
    }
}
