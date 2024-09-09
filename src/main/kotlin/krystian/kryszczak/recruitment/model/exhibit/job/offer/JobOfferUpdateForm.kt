package krystian.kryszczak.recruitment.model.exhibit.job.offer

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm

/**
 * If any variable has a null value, it should not be changed.
 */
@Serdeable
data class JobOfferUpdateForm(
    val title: String? = null,
    val description: Map<String, String>? = null,
    val mainTechnology: String? = null,
    val typeOfWork: TypeOfWork? = null,
    val experience: Experience? = null,
    val employmentType: EmploymentType? = null,
    val minEarningsPerMonth: Int? = null,
    val maxEarningsPerMonth: Int? = null,
    val currency: String? = null,
    val techStack: Map<String, Byte>? = null,
    val places: Array<String>? = null,
    val recruitmentType: RecruitmentType? = null,
    val operatingMode: OperatingMode? = null
) : ExhibitUpdateForm<JobOffer, JobOfferUpdateForm> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobOfferUpdateForm

        if (title != other.title) return false
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
        if (operatingMode != other.operatingMode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (mainTechnology?.hashCode() ?: 0)
        result = 31 * result + (typeOfWork?.hashCode() ?: 0)
        result = 31 * result + (experience?.hashCode() ?: 0)
        result = 31 * result + (employmentType?.hashCode() ?: 0)
        result = 31 * result + (minEarningsPerMonth ?: 0)
        result = 31 * result + (maxEarningsPerMonth ?: 0)
        result = 31 * result + (currency?.hashCode() ?: 0)
        result = 31 * result + (techStack?.hashCode() ?: 0)
        result = 31 * result + (places?.contentHashCode() ?: 0)
        result = 31 * result + (recruitmentType?.hashCode() ?: 0)
        result = 31 * result + (operatingMode?.hashCode() ?: 0)
        return result
    }
}
