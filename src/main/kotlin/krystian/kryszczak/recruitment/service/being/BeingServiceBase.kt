package krystian.kryszczak.recruitment.service.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.being.BeingRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.service.AbstractRestrictableDataAccessService
import reactor.core.publisher.Mono

abstract class BeingServiceBase<T : Being<T>, S : BeingDto<T, S>, V : BeingCreationForm<T, V>, U : BeingUpdateForm<T, U>, C : BeingCredentials, A : BeingActivation<T, V, C>>(
    override val repository: BeingRepository<T>,
    private val passwordEncoder: PasswordEncoder,
    private val credentialsRepository: BeingCredentialsRepository<C>,
    mapper: BeingMapper<T, S, V, U, C, A>
) : BeingService<T, V, U>, AbstractRestrictableDataAccessService<T, S, V, U, String>(repository, mapper) {
    override fun findByEmail(username: String): Mono<T> = repository.findByEmail(username)

    override fun autoDeleteByUser(password: String, clientId: String): Mono<Boolean> =
        credentialsRepository.findById(clientId)
            .filter { passwordEncoder.matches(password, it.secret) }
            .flatMap {
                Mono.zip(
                    repository.deleteById(clientId).hasElement(),
                    credentialsRepository.deleteById(clientId).hasElement(),
                    Boolean::and
                )
            }.defaultIfEmpty(false)
}
