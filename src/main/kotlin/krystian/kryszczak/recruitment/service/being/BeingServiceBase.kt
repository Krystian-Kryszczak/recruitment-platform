package krystian.kryszczak.recruitment.service.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.being.BeingRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.BeingActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.security.validation.PasswordValidator
import krystian.kryszczak.recruitment.service.AbstractExtendedDataAccessService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class BeingServiceBase<T : Being, S : BeingDto<T, S>, V : BeingCreationForm<T, V>, U : BeingUpdateForm<T, U>, C : BeingCredentials, A : BeingActivation<T, V, C>>(
    repository: BeingRepository<T>,
    private val passwordEncoder: PasswordEncoder,
    private val smtpMailerService: SmtpMailerService,
    private val activationRepository: BeingActivationRepository<A>,
    private val credentialsRepository: BeingCredentialsRepository<C>,
    private val metricsService: MetricsService,
    private val generator: ActivationCodeGenerator,
    private val mapper: BeingMapper<T, S, V, U, C, A>,
    private val createWithGeneratedCode: (identity: String, formation: V,
                                          encodedPassword: String, generator: ActivationCodeGenerator) -> A
) : BeingService<T, V, U>, AbstractExtendedDataAccessService<T, S, V, U, String>(repository, mapper) {
    override fun autoDeleteByUser(password: String, clientId: String): Mono<Boolean> {
        return credentialsRepository.findById(clientId).map {
            passwordEncoder.matches(password, it.secret)
        }.thenReturn(true)
        .defaultIfEmpty(false)
    }

    override fun register(creationForm: V, password: String): Mono<Boolean> {
        if (!PasswordValidator.validate(password)) {
            logger.debug("Invalid user password! User email: ${creationForm.email}")
            return Mono.just(false)
        }

        return generateActivationCodeForUser(creationForm, passwordEncoder.encode(password), generator)
            .doOnSuccess { code ->
                val email: String = creationForm.email
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
            .flatMap {
                mapper.mapToOriginItem(it)
                    .flatMap(super.repository::save)
                    .zipWith(credentialsRepository.save(mapper.mapToCredentials(it)))
            }.doOnSuccess {
                logger.info("Successful activated account with email $email")
                metricsService.incrementActivatedAccounts()
            }.thenReturn(true)
            .doOnError { metricsService.incrementActivationAccountFails() }
            .doOnError { logger.error(it.message, it) }
            .onErrorReturn(false)
            .switchIfEmpty(Mono.fromCallable { metricsService.incrementActivationAccountFails(); false })
    }

    private fun generateActivationCodeForUser(formation: V, encodedPassword: String, generator: ActivationCodeGenerator): Mono<String> =
        activationRepository.save(
            createWithGeneratedCode(formation.email, formation, encodedPassword, generator)
        ).doOnSuccess {
            metricsService.incrementGeneratedActivationCodes()
        }.map { it.code }

    private fun activationCodeMatches(userEmail: String, code: String): Flux<A> =
        activationRepository.findByIdentityAndCode(userEmail, code)

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BeingServiceBase::class.java)
    }
}
