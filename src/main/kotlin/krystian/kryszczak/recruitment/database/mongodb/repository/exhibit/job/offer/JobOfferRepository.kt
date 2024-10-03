package krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.offer

import io.micronaut.data.model.Pageable
import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.ExhibitRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@MongoRepository
interface JobOfferRepository : ExhibitRepository<JobOffer> {
    fun findByPathArrayContains(path: String): Mono<JobOffer>

    fun existsByPathArrayContains(path: String): Mono<Boolean>

    fun findByEmployerId(employerId: String, pageable: Pageable): Flux<JobOffer>

    fun searchByTitleOrMainTechnologyOrTypeOfWorkOrExperienceOrEmploymentTypeOrMinEarningsPerMonthOrMaxEarningsPerMonthOrLocationsCollectionContainsOrRecruitmentTypeOrOperatingMode(
        title: String?,
        mainTechnology: String?,
        typeOfWork: TypeOfWork?,
        experience: Experience?,
        employmentType: EmploymentType?,
        minEarningsPerMonth: Int?,
        maxEarningsPerMonth: Int?,
        location: String?,
        recruitmentType: RecruitmentType?,
        operatingMode: OperatingMode?,
        pageable: Pageable
    ): Flux<JobOffer>
}
