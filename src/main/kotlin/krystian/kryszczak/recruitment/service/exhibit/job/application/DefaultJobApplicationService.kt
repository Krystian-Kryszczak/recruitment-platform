package krystian.kryszczak.recruitment.service.exhibit.job.application

import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.application.JobApplicationRepository
import krystian.kryszczak.recruitment.extension.authentication.getClientId
import krystian.kryszczak.recruitment.extension.authentication.isEmployer
import krystian.kryszczak.recruitment.mapper.exhibit.job.application.JobApplicationMapper
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.exhibit.job.ExhibitServiceBase
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class DefaultJobApplicationService(
    override val repository: JobApplicationRepository,
    private val mapper: JobApplicationMapper
) : ExhibitServiceBase<JobApplication, JobApplicationDto, JobApplicationCreationForm, JobApplicationUpdateForm, String>(
    repository, mapper
), JobApplicationService {
    override fun addByCandidate(form: JobApplicationCreationForm, authentication: Authentication?): Mono<JobApplication> {
        if (authentication?.isEmployer() == true) return Mono.empty()
        return mapper.mapToOriginItem(form, authentication?.getClientId())
            .flatMap(repository::save)
    }

    override fun findByIdForEmployerClient(id: String, authentication: Authentication): Mono<JobApplication> =
        authentication.getClientId()?.let {
            repository.findById(id)
        } ?: Mono.empty()

    override fun findByOfferIdForEmployerClient(offerId: String, authentication: Authentication, page: Int): Flux<JobApplication> =
        authentication.getClientId()?.let {
            repository.findByOfferId(offerId, Pageable.from(page, 12))
        } ?: Flux.empty()

    override fun findPublishedByCandidateClient(authentication: Authentication, page: Int): Flux<JobApplication> =
        authentication.getClientId()?.let { clientId ->
            repository.findByCandidateId(clientId, Pageable.from(page, 12))
        } ?: Flux.empty()

    override fun deleteOwnByIdForCandidateClient(id: String, authentication: Authentication): Mono<Boolean> =
        Mono.justOrEmpty<String>(authentication.getClientId())
            .flatMap { clientId ->
                repository.findById(id)
                    .filter { it.candidateId == clientId }
                    .flatMap(repository::delete)
                    .hasElement()
            }
}
