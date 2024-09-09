package krystian.kryszczak.recruitment.service.job.offer

import io.micronaut.data.model.Pageable
import io.micronaut.data.model.Sort
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.extension.authentication.getClientId
import krystian.kryszczak.recruitment.model.constant.SortBy
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.offer.JobOfferRepository
import krystian.kryszczak.recruitment.mapper.exhibit.job.offer.JobOfferMapper
import krystian.kryszczak.recruitment.model.exhibit.job.offer.*
import krystian.kryszczak.recruitment.service.AbstractExtendedDataAccessService
import krystian.kryszczak.recruitment.service.moderation.ModerationService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.StringBuilder

@Singleton
class DefaultJobOfferService(
    override val repository: JobOfferRepository,
    private val mapper: JobOfferMapper,
    private val moderationService: ModerationService
) : JobOfferService, AbstractExtendedDataAccessService<JobOffer, JobOfferDto, JobOfferCreationForm, JobOfferUpdateForm, String>(repository, mapper) {
    override fun findByPathOrId(data: String): Mono<JobOffer> = findByPath(data)
        .switchIfEmpty(Mono.defer { findById(data) })

    override fun findByPath(path: String) = repository.findByPath(path)

    override fun existsByPath(path: String): Mono<Boolean> = repository.existsByPath(path)

    override fun search(bean: JobOfferQuery) = with(bean) {
        repository.findByTitleLike( // TODO refactor and test
            keyword,
            mainTechnology,
            typeOfWork,
            experience,
            employmentType,
            salaryMin,
            salaryMax,
            location,
            recruitmentType,
            remote,
            Pageable.from(page ?: 0, 5, extractSort(bean)),
        )
    }

    override fun findByEmployerId(id: String, page: Int?, authentication: Authentication?): Flux<JobOffer> {
        TODO("Not yet implemented")
    }

    private fun extractSort(bean: JobOfferQuery) = when (bean.sortBy) {
        SortBy.LATEST -> Sort.of(Sort.Order("dateCreated", Sort.Order.Direction.DESC, false))
        SortBy.HIGHEST_SALARY -> Sort.of(Sort.Order("maxEarningsPerMonth", Sort.Order.Direction.DESC, false))
        SortBy.LOWEST_SALARY -> Sort.of(Sort.Order("minEarningsPerMonth", Sort.Order.Direction.ASC, false))
        else -> Sort.unsorted()
    }

    override fun employerAdd(creation: JobOfferCreationForm, authentication: Authentication): Mono<JobOffer> {
        if (!authentication.roles.contains("EMPLOYER")) return Mono.empty()
        val clientId = authentication.getClientId() ?: return Mono.empty()
        return checkIfCreationIsHarmful(creation)
            .filter(Boolean::not)
            .flatMap { mapper.mapToOriginItem(creation, clientId) }
            .flatMap(repository::save)
    }

    override fun employerUpdate(id: String, updateForm: JobOfferUpdateForm, authentication: Authentication): Mono<Boolean> {
        if (!authentication.roles.contains("EMPLOYER")) return Mono.just(false)
        val clientId = authentication.getClientId() ?: return Mono.just(false)
        return checkIfCreationIsHarmful(updateForm).flatMap { bool ->
            if (bool) Mono.just(false)
            else repository.findById(id)
                .filter { it.employerId == clientId }
                .flatMap { mapper.mapToOriginItem(it, updateForm) }
                .flatMap(::update)
                .hasElement()
        }
    }

    override fun employerRemove(id: String, authentication: Authentication): Mono<Boolean> {
        if (!authentication.roles.contains("EMPLOYER")) return Mono.just(false)
        val clientId = authentication.getClientId() ?: return Mono.just(false)
        return repository.findById(clientId).flatMap {
            if (it.employerId == clientId) repository.delete(it).thenReturn(true)
            else Mono.just(false)
        }
    }

    override fun findByEmployerAuth(page: Int?, authentication: Authentication): Flux<JobOffer> {
        if (!authentication.roles.contains("EMPLOYER")) return Flux.empty()
        val clientId = authentication.getClientId() ?: return Flux.empty()
        return repository.findByEmployerId(clientId)
    }

    private fun checkIfCreationIsHarmful(creationForm: JobOfferCreationForm): Mono<Boolean> {
        val builder = StringBuilder(creationForm.title).append(" ")
            .append(creationForm.mainTechnology).append(" ")
            .append(creationForm.currency).append(" ")
        creationForm.description.forEach { (key, value) -> builder.append(" ").append(key).append(" ").append(value) }
        creationForm.places?.forEach { builder.append(" ").append(it) }
        creationForm.techStack?.forEach { builder.append(" ").append(it) }
        return moderationService.checkIfInputIsHarmful(builder.toString(), "change this") // TODO
    }

    private fun checkIfCreationIsHarmful(updateForm: JobOfferUpdateForm): Mono<Boolean> {
        val builder = StringBuilder(updateForm.title).append(" ")
            .append(updateForm.mainTechnology).append(" ")
            .append(updateForm.currency).append(" ")
        updateForm.description?.forEach { (key, value) -> builder.append(" ").append(key).append(" ").append(value) }
        updateForm.places?.forEach { builder.append(" ").append(it) }
        updateForm.techStack?.forEach { builder.append(" ").append(it) }
        return moderationService.checkIfInputIsHarmful(builder.toString(), "change this") // TODO
    }
}
