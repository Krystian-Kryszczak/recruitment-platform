package krystian.kryszczak.recruitment.service.exhibit.job.offer

import io.micronaut.data.model.Pageable
import io.micronaut.data.model.Sort
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.extension.authentication.getClientId
import krystian.kryszczak.recruitment.model.constant.SortBy
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.offer.JobOfferRepository
import krystian.kryszczak.recruitment.mapper.exhibit.job.offer.JobOfferMapper
import krystian.kryszczak.recruitment.model.exhibit.job.offer.*
import krystian.kryszczak.recruitment.service.exhibit.job.ExhibitServiceBase
import org.bson.types.ObjectId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class DefaultJobOfferService(
    override val repository: JobOfferRepository,
    private val mapper: JobOfferMapper
) : ExhibitServiceBase<JobOffer, JobOfferDto, JobOfferCreationForm, JobOfferUpdateForm, String>(
    repository, mapper
), JobOfferService {
    override fun findByPathOrId(data: String): Mono<JobOffer> =
        if (ObjectId.isValid(data)) findById(data) else findByPath(data)

    override fun findByPath(path: String) = repository.findByPathArrayContains(path)

    override fun existsByPath(path: String): Mono<Boolean> = repository.existsByPathArrayContains(path)

    override fun search(bean: JobOfferQuery): Flux<JobOffer> = with(bean) {
        repository.searchByTitleOrMainTechnologyOrTypeOfWorkOrExperienceOrEmploymentTypeOrMinEarningsPerMonthOrMaxEarningsPerMonthOrLocationsCollectionContainsOrRecruitmentTypeOrOperatingMode(
            keyword,
            mainTechnology,
            typeOfWork,
            experience,
            employmentType,
            salaryMin,
            salaryMax,
            location,
            recruitmentType,
            operatingMode,
            Pageable.from(page ?: 0, 12, extractSort(bean)),
        ).filter()
    }

    override fun findByEmployerId(employerId: String, page: Int, authentication: Authentication?): Flux<JobOffer> {
        return repository.findByEmployerId(employerId, Pageable.from(page, 12))
            .filter { it.filterTest() || authentication?.getClientId() == it.employerId }
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
        return mapper.mapToOriginItem(creation, clientId)
            .flatMap(repository::save)
    }

    override fun employerUpdate(id: String, updateForm: JobOfferUpdateForm, authentication: Authentication): Mono<Boolean> {
        if (!authentication.roles.contains("EMPLOYER")) return Mono.just(false)
        val clientId = authentication.getClientId() ?: return Mono.just(false)
        return repository.findById(id)
            .filter { it.employerId == clientId }
            .flatMap { mapper.mapToOriginItem(it, updateForm) }
            .flatMap(::update)
            .hasElement()
    }

    override fun employerRemove(id: String, authentication: Authentication): Mono<Boolean> {
        if (!authentication.roles.contains("EMPLOYER")) return Mono.just(false)
        val clientId = authentication.getClientId() ?: return Mono.just(false)
        return repository.findById(clientId)
            .filter { it.employerId == clientId }
            .flatMap { repository.delete(it).thenReturn(true) }
            .defaultIfEmpty(false)
    }

    override fun findByEmployerAuth(page: Int, authentication: Authentication): Flux<JobOffer> {
        if (!authentication.roles.contains("EMPLOYER")) return Flux.empty()
        val clientId = authentication.getClientId() ?: return Flux.empty()
        return repository.findByEmployerId(clientId, Pageable.from(page, 12))
    }
}
