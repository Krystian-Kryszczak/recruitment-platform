package krystian.kryszczak.recruitment.repository.job.offer

import io.micronaut.data.model.Pageable
import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@MongoRepository
interface JobOfferRepository : CrudRepositoryBase<JobOffer> {
    fun findByPath(path: String): Mono<JobOffer>

    fun findByEmployerId(employerId: String): Flux<JobOffer>

    fun findByTitleLike(
        title: String?,
        mainTechnology: String?,
        typeOfWork: TypeOfWork?,
        experience: Experience?,
        employmentType: EmploymentType?,
        minEarningsPerMonth: Int?,
        maxEarningsPerMonth: Int?,
        location: String?,
        recruitmentType: RecruitmentType?,
        remote: Boolean?,
        pageable: Pageable
    ): Flux<JobOffer>
}
