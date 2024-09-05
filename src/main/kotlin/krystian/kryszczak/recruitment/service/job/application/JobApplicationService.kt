package krystian.kryszczak.recruitment.service.job.application

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.model.job.application.JobApplication
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface JobApplicationService : DataAccessService<JobApplication, String> {
    fun findByIdForEmployerClient(id: String, authentication: Authentication): Mono<JobApplication>
    fun findByOfferIdForEmployerClient(id: String, authentication: Authentication): Flux<JobApplication>
    fun findPublishedByCandidateClient(authentication: Authentication): Flux<JobApplication>
    fun deleteOwnByIdForCandidateClient(id: String, authentication: Authentication): Mono<Boolean>
}
