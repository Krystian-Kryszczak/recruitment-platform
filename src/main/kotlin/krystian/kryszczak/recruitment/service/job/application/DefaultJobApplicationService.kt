package krystian.kryszczak.recruitment.service.job.application

import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.application.JobApplicationRepository
import krystian.kryszczak.recruitment.mapper.exhibit.job.application.JobApplicationMapper
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.AbstractExtendedDataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class DefaultJobApplicationService(repository: JobApplicationRepository, mapper: JobApplicationMapper) : JobApplicationService,
AbstractExtendedDataAccessService<JobApplication, JobApplicationDto, JobApplicationCreationForm, JobApplicationUpdateForm, String>(repository, mapper) {
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
