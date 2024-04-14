package krystian.kryszczak.recruitment.service.job.application

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.model.job.application.JobApplication
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface JobApplicationService : DataAccessService<JobApplication, String> {
    fun findByEmployerClient(id: String, authentication: Authentication): Mono<JobApplication>

    fun findSentByCandidateClient(authentication: Authentication): Flux<JobApplication>
    fun findByIdSentByCandidateClient(id: String, authentication: Authentication): Flux<JobApplication>
    fun cancelOwnById(id: String, authentication: Authentication): Mono<Boolean>
}
