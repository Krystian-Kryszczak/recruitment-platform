package krystian.kryszczak.recruitment.service.exhibit.job.application

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.exhibit.ExhibitService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface JobApplicationService : ExhibitService<JobApplication, JobApplicationUpdateForm, String> {
    fun addByCandidate(form: JobApplicationCreationForm, authentication: Authentication?): Mono<JobApplication>
    fun findByIdForEmployerClient(id: String, authentication: Authentication): Mono<JobApplication>
    fun findByOfferIdForEmployerClient(offerId: String, authentication: Authentication, page: Int): Flux<JobApplication>
    fun findPublishedByCandidateClient(authentication: Authentication, page: Int): Flux<JobApplication>
    fun deleteOwnByIdForCandidateClient(id: String, authentication: Authentication): Mono<Boolean>
}
