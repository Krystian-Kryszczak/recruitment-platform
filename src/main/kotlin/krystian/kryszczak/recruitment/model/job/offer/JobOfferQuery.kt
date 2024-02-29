package krystian.kryszczak.recruitment.model.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.authentication.Authentication
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.constant.*

@Introspected
data class JobOfferQuery(
    @field:QueryValue val query: String?,
    @field:QueryValue("main-technology") val mainTechnology: String?,
    @field:QueryValue val location: String?,
    @field:QueryValue val remote: Boolean?,
    @field:QueryValue("only-with-salary") val onlyWithSalary: Boolean?,
    @field:QueryValue("salary-min") @field:PositiveOrZero val salaryMin: Int?,
    @field:QueryValue("salary-max") @field:PositiveOrZero val salaryMax: Int?,
    @field:QueryValue val experience: Experience?,
    @field:QueryValue("employment-type") val employmentType: EmploymentType?,
    @field:QueryValue("type-of-work") val typeOfWork: TypeOfWork?,
    @field:QueryValue("recruitment-type") val recruitmentType: RecruitmentType?,
    @field:QueryValue val page: Int?,
    @field:QueryValue("sort-by") val sortBy: SortBy?,
    val authentication: Authentication?
)
