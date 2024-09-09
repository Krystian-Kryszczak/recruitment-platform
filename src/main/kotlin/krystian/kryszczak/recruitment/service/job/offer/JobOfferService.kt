package krystian.kryszczak.recruitment.service.job.offer

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferQuery
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface JobOfferService : DataAccessService<JobOffer, String> {
    fun findByPathOrId(data: String): Mono<JobOffer>

    fun findByPath(path: String): Mono<JobOffer>

    fun existsByPath(path: String): Mono<Boolean>

    fun search(bean: JobOfferQuery): Flux<JobOffer>

    fun findByEmployerId(id: String, page: Int?, authentication: Authentication?): Flux<JobOffer>

    fun findByEmployerAuth(page: Int?, authentication: Authentication): Flux<JobOffer>

    fun employerAdd(creation: JobOfferCreationForm, authentication: Authentication): Mono<JobOffer>

    fun employerUpdate(id: String, updateForm: JobOfferUpdateForm, authentication: Authentication): Mono<Boolean>

    fun employerRemove(id: String, authentication: Authentication): Mono<Boolean>
}
