package krystian.kryszczak.recruitment.service.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingFormation
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.repository.being.BeingRepository
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepository
import krystian.kryszczak.recruitment.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.security.validation.PasswordValidator
import krystian.kryszczak.recruitment.service.DataAccessServiceBase
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class BeingServiceBase<T : Being, S : BeingFormation<T>, U : BeingCredentials, V : BeingActivation<T, S, U>>(
    repository: BeingRepository<T>,
    private val passwordEncoder: PasswordEncoder,
    private val smtpMailerService: SmtpMailerService,
    private val activationRepository: BeingActivationRepository<V>,
    private val credentialsRepository: BeingCredentialsRepository<U>,
    private val metricsService: MetricsService,
    private val generator: ActivationCodeGenerator,
    private val createWithGeneratedCode: (identity: String, formation: S,
        encodedPassword: String, generator: ActivationCodeGenerator) -> V
) : BeingService<T, S>, DataAccessServiceBase<T, String>(repository) {
    override fun autoDeleteByUser(password: String, clientId: String): Mono<Boolean> {
        return credentialsRepository.findById(clientId).map {
            passwordEncoder.matches(password, it.secret)
        }.thenReturn(true)
        .defaultIfEmpty(false)
    }

    override fun register(formation: S, password: String): Mono<Boolean> {
        if (!PasswordValidator.validate(password)) {
            logger.debug("Invalid user password! User email: ${formation.email}")
            return Mono.just(false)
        }

        return generateActivationCodeForUser(formation, passwordEncoder.encode(password), generator)
            .doOnSuccess { code ->
                val email: String = formation.email
                logger.info("The activation code `$code` will be sent to the email address $email")
                smtpMailerService.sendUserActivationCode(email, code)
            }.thenReturn(true)
            .doOnError { logger.error(it.message, it) }
            .onErrorReturn(false)
    }

    override fun completeActivation(email: String, code: String): Mono<Boolean> {
        return activationCodeMatches(email, code)
            .flatMap { activationRepository.delete(it).thenReturn(it) }
            .single()
            .flatMap { super.repository.save(it.toFormationTarget()).thenReturn(it) }
            .flatMap { credentialsRepository.save(it.toCredentials()).thenReturn(it) }
            .doOnSuccess {
                logger.info("Successful activated account with email $email")
                metricsService.incrementActivatedAccounts()
            }.thenReturn(true)
            .doOnError { metricsService.incrementActivationAccountFails() }
            .doOnError { logger.error(it.message, it) }
            .onErrorReturn(false)
            .switchIfEmpty(Mono.fromCallable { metricsService.incrementActivationAccountFails(); false })
    }

    private fun generateActivationCodeForUser(formation: S, encodedPassword: String, generator: ActivationCodeGenerator): Mono<String> =
        activationRepository.save(
            createWithGeneratedCode(formation.email, formation, encodedPassword, generator)
        ).doOnSuccess {
            metricsService.incrementGeneratedActivationCodes()
        }.map { it.code }

    private fun activationCodeMatches(userEmail: String, code: String): Flux<V> =
        activationRepository.findByIdentityAndCode(userEmail, code)

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BeingServiceBase::class.java)
    }
}
