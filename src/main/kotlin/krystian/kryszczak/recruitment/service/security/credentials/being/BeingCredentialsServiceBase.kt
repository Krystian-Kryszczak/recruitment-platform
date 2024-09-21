package krystian.kryszczak.recruitment.service.security.credentials.being

import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.service.AbstractDataAccessService
import reactor.core.publisher.Mono

abstract class BeingCredentialsServiceBase<T : BeingCredentials>(override val repository: BeingCredentialsRepository<T>) :
AbstractDataAccessService<T, String>(repository), BeingCredentialsService<T> {
    override fun findByUsername(username: String): Mono<T> = repository.findByUsername(username)
    override fun updatePasswordById(id: String, password: String): Mono<Long> = repository.updateHashedPasswordById(id, password)
}
