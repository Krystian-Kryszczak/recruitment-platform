package krystian.kryszczak.recruitment.service.exhibit.job.offer

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferQuery
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.exhibit.ExhibitService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface JobOfferService : ExhibitService<JobOffer, JobOfferUpdateForm, String> {
    fun findByPathOrId(data: String): Mono<JobOffer>

    fun findByPath(path: String): Mono<JobOffer>

    fun existsByPath(path: String): Mono<Boolean>

    fun search(bean: JobOfferQuery): Flux<JobOffer>

    fun findByEmployerId(employerId: String, page: Int = 0, authentication: Authentication?): Flux<JobOffer>

    fun findByEmployerAuth(page: Int = 0, authentication: Authentication): Flux<JobOffer>

    fun employerAdd(creation: JobOfferCreationForm, authentication: Authentication): Mono<JobOffer>

    fun employerUpdate(id: String, updateForm: JobOfferUpdateForm, authentication: Authentication): Mono<Boolean>

    fun employerRemove(id: String, authentication: Authentication): Mono<Boolean>
}
