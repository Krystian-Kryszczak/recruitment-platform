package krystian.kryszczak.recruitment.model.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Dto
import krystian.kryszczak.recruitment.model.constant.EmploymentType
import krystian.kryszczak.recruitment.model.constant.Experience
import krystian.kryszczak.recruitment.model.constant.RecruitmentType
import krystian.kryszczak.recruitment.model.constant.TypeOfWork
import java.time.Instant

@Serdeable
@Introspected
data class JobOfferDto(
    val id: String?,
    val title: String,
    val employerId: String,
    val description: Map<String, String>,
    val mainTechnology: String,
    val typeOfWork: TypeOfWork,
    val experience: Experience,
    val employmentType: EmploymentType,
    val minEarningsPerMonth: Int,
    val maxEarningsPerMonth: Int,
    val currency: String,
    val techStack: Map<String, Byte>?,
    val places: Array<String>?,
    val recruitmentType: RecruitmentType,
    val remote: Boolean,
    val expires: Instant,
    val path: String,
    val dateCreated: Instant?
) : Dto<JobOffer, JobOfferDto> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobOfferDto

        if (id != other.id) return false
        if (title != other.title) return false
        if (employerId != other.employerId) return false
        if (description != other.description) return false
        if (mainTechnology != other.mainTechnology) return false
        if (typeOfWork != other.typeOfWork) return false
        if (experience != other.experience) return false
        if (employmentType != other.employmentType) return false
        if (minEarningsPerMonth != other.minEarningsPerMonth) return false
        if (maxEarningsPerMonth != other.maxEarningsPerMonth) return false
        if (currency != other.currency) return false
        if (techStack != other.techStack) return false
        if (places != null) {
            if (other.places == null) return false
            if (!places.contentEquals(other.places)) return false
        } else if (other.places != null) return false
        if (recruitmentType != other.recruitmentType) return false
        if (remote != other.remote) return false
        if (expires != other.expires) return false
        if (path != other.path) return false
        if (dateCreated != other.dateCreated) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + employerId.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + mainTechnology.hashCode()
        result = 31 * result + typeOfWork.hashCode()
        result = 31 * result + experience.hashCode()
        result = 31 * result + employmentType.hashCode()
        result = 31 * result + minEarningsPerMonth
        result = 31 * result + maxEarningsPerMonth
        result = 31 * result + currency.hashCode()
        result = 31 * result + (techStack?.hashCode() ?: 0)
        result = 31 * result + (places?.contentHashCode() ?: 0)
        result = 31 * result + recruitmentType.hashCode()
        result = 31 * result + remote.hashCode()
        result = 31 * result + expires.hashCode()
        result = 31 * result + path.hashCode()
        result = 31 * result + (dateCreated?.hashCode() ?: 0)
        return result
    }

    companion object : Dto.Mapper<JobOffer, JobOfferDto> {
        override fun from(item: JobOffer) = JobOfferDto(
            item.id,
            item.title,
            item.employerId,
            item.description,
            item.mainTechnology,
            item.typeOfWork,
            item.experience,
            item.employmentType,
            item.minEarningsPerMonth,
            item.maxEarningsPerMonth,
            item.currency,
            item.techStack,
            item.places,
            item.recruitmentType,
            item.remote,
            item.expires,
            item.path,
            item.dateCreated
        )
    }
}
