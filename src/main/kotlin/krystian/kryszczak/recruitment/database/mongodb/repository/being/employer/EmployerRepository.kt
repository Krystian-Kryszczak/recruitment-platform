package krystian.kryszczak.recruitment.database.mongodb.repository.being.employer

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.database.mongodb.repository.being.BeingRepository
import reactor.core.publisher.Mono

@MongoRepository
interface EmployerRepository : BeingRepository<Employer> {
    fun findNameById(id: String): Mono<String>
}
