package krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.application

import io.micronaut.data.model.Pageable
import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.ExhibitRepository
import reactor.core.publisher.Flux

@MongoRepository
interface JobApplicationRepository : ExhibitRepository<JobApplication> {
    fun findByCandidateId(candidateId: String, pageable: Pageable): Flux<JobApplication>
    fun findByOfferId(offerId: String, pageable: Pageable): Flux<JobApplication>
}
