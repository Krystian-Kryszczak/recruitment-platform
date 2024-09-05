package krystian.kryszczak.recruitment.service.job.application

import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.job.application.JobApplication
import krystian.kryszczak.recruitment.repository.job.application.JobApplicationRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class JobApplicationServiceImpl(repository: JobApplicationRepository) : JobApplicationService, DataAccessServiceImpl<JobApplication>(repository) {
    override fun findByIdForEmployerClient(id: String, authentication: Authentication): Mono<JobApplication> {
        TODO("Not yet implemented")
    }

    override fun findByOfferIdForEmployerClient(id: String, authentication: Authentication): Flux<JobApplication> {
        TODO("Not yet implemented")
    }

    override fun findPublishedByCandidateClient(authentication: Authentication): Flux<JobApplication> {
        TODO("Not yet implemented")
    }

    override fun deleteOwnByIdForCandidateClient(id: String, authentication: Authentication): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun <S : JobApplication> saveAll(entities: Iterable<S>): Flux<S> {
        TODO("Not yet implemented")
    }
}
