package krystian.kryszczak.recruitment.database.mongodb.repository.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import reactor.core.publisher.Mono

interface BeingRepository<T : Being<T>> : CrudRepositoryBase<T, String> {
    fun findByEmail(username: String): Mono<T>
}
