package krystian.kryszczak.recruitment.service.security.authentication

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailed
import io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class AuthenticationServiceImpl(
//    private val userCredentialsDao: UserCredentialsDao,
//    private val userService: UserService,
//    private val passwordEncoder: PasswordEncoder,
//    private val metricsService: MetricsService
): AuthenticationService {
    override fun authenticate( // TODO
        context: HttpRequest<*>,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Mono<AuthenticationResponse> {
        val login: String = authenticationRequest.identity.toString()
        val password: String = authenticationRequest.secret.toString()

        return Mono.just(AuthenticationResponse.failure())
//        return Mono.from(userCredentialsDao.findByEmail(login))
//            .flatMap { credentials ->
//                if (credentials.hashedPassword != null && passwordEncoder.matches(password, credentials.hashedPassword)) Maybe.just(credentials) else Maybe.empty()
//            }.flatMap {
//                userService.findByEmail(login).map { AuthenticationResponse.success(login, it.extractUserAttributes) }
//            }.switchIfEmpty(Mono.just(AuthenticationFailed(CREDENTIALS_DO_NOT_MATCH)))
//            .toFlowable()
//            .doOnNext {
//                metricsService.incrementLoginAttempts()
//                if (it.isAuthenticated) {
//                    metricsService.incrementLoginSuccessful()
//                    logger.info("User with email address $login login successfully logged in.")
//                }
//            }
    }

    companion object {
        @JvmStatic
        private val logger = LoggerFactory.getLogger(AuthenticationServiceImpl::class.java)
    }
}
