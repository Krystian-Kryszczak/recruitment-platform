package krystian.kryszczak.recruitment.service.security.registration.being

import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.BeingActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.security.validation.PasswordValidator
import krystian.kryszczak.recruitment.service.being.BeingService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

abstract class BeingRegistrationServiceBase<T : Being<T>, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>, D : BeingDto<T, D>, C : BeingCredentials, A : BeingActivation<T, S, C>>(
    private val activationRepository: BeingActivationRepository<A>,
    private val credentialsRepository: BeingCredentialsRepository<C>,
    private val beingService: BeingService<T, S, U>,
    private val beingMapper: BeingMapper<T, D, S, U, C, A>,
    private val smtpMailerService: SmtpMailerService
) : BeingRegistrationService<T, S, U, D, C, A> {
    override fun register(form: S): Mono<Boolean> = with(form) {
        if (PasswordValidator.validate(password)) {
            beingMapper.mapToRegisterActivation(form)
                .flatMap(activationRepository::save)
                .doOnSuccess { smtpMailerService.sendActivationCode(it.identity, it.code) }
                .hasElement()
                .onErrorReturn(false)
        } else {
            Mono.just(false)
        }
    }

    override fun completeAccountActivation(email: String, code: String): Mono<Boolean> {
        return activationCodeMatches(email, code)
            .flatMap {
                Mono.zip(
                    beingMapper.mapToOriginItem(it).flatMap(beingService::save),
                    credentialsRepository.save(beingMapper.mapToCredentials(it)),
                    activationRepository.delete(it)
                )
            }.doOnSuccess { logger.info("Successful activated account with email $email") }
            .hasElement()
            .doOnError { logger.error(it.message, it) }
            .onErrorReturn(false)
    }

    private fun activationCodeMatches(userEmail: String, code: String): Mono<A> =
        activationRepository.findByIdentityAndCode(userEmail, code).singleOrEmpty()

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BeingRegistrationServiceBase::class.java)
    }
}
