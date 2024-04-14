package krystian.kryszczak.recruitment.service.job.offer

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.job.offer.JobOfferQuery
import krystian.kryszczak.recruitment.model.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface JobOfferService : DataAccessService<JobOffer, String> {
    fun findByPath(path: String): Mono<JobOffer>

    fun search(bean: JobOfferQuery): Flux<JobOffer>

    fun getEmployerOffers(authentication: Authentication): Flux<JobOffer>

    fun employerAdd(creation: JobOfferCreationForm, authentication: Authentication): Mono<JobOffer>

    fun employerUpdate(id: String, updateForm: JobOfferUpdateForm, authentication: Authentication): Mono<Boolean>

    fun employerRemove(id: String, authentication: Authentication): Mono<Boolean>
}
