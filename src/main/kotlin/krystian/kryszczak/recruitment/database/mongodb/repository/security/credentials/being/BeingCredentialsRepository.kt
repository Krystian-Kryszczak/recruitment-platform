package krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being

import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.CredentialsRepository
import reactor.core.publisher.Mono

interface BeingCredentialsRepository<T : BeingCredentials> : CredentialsRepository<T> {
    fun findByUsername(username: String): Mono<T>
    fun updateHashedPasswordById(id: String, hashedPassword: String): Mono<Long>
}
