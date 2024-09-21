package krystian.kryszczak.recruitment.model.exhibit.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.authentication.Authentication
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.constant.*

@Introspected
data class JobOfferQuery(
    @field:QueryValue val keyword: String? = null,
    @field:QueryValue("main-technology") val mainTechnology: String? = null,
    @field:QueryValue val location: String? = null,
    @field:QueryValue val remote: Boolean? = null,
    @field:QueryValue("only-with-salary") val onlyWithSalary: Boolean? = null,
    @field:QueryValue("salary-min") @field:PositiveOrZero val salaryMin: Int? = null,
    @field:QueryValue("salary-max") @field:PositiveOrZero val salaryMax: Int? = null,
    @field:QueryValue val experience: Experience? = null,
    @field:QueryValue("employment-type") val employmentType: EmploymentType? = null,
    @field:QueryValue("type-of-work") val typeOfWork: TypeOfWork? = null,
    @field:QueryValue("recruitment-type") val recruitmentType: RecruitmentType? = null,
    @field:QueryValue val page: Int? = null,
    @field:QueryValue("sort-by") val sortBy: SortBy? = null,
    val authentication: Authentication? = null
)
