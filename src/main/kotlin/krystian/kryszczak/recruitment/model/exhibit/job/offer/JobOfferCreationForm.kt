package krystian.kryszczak.recruitment.model.exhibit.job.offer

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.ExhibitCreationForm

@Serdeable
data class JobOfferCreationForm(
    val tierId: String,
    val title: String,
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
    val operatingMode: OperatingMode
) : ExhibitCreationForm<JobOffer, JobOfferCreationForm> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobOfferCreationForm

        if (title != other.title) return false
        if (description != other.description) return false
        if (mainTechnology != other.mainTechnology) return false
        if (typeOfWork != other.typeOfWork) return false
        if (experience != other.experience) return false
        if (employmentType != other.employmentType) return false
        if (minEarningsPerMonth != other.minEarningsPerMonth) return false
        if (maxEarningsPerMonth != other.maxEarningsPerMonth) return false
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
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + mainTechnology.hashCode()
        result = 31 * result + typeOfWork.hashCode()
        result = 31 * result + experience.hashCode()
        result = 31 * result + employmentType.hashCode()
        result = 31 * result + minEarningsPerMonth
        result = 31 * result + maxEarningsPerMonth
        result = 31 * result + (techStack?.hashCode() ?: 0)
        result = 31 * result + (places?.contentHashCode() ?: 0)
        result = 31 * result + recruitmentType.hashCode()
        result = 31 * result + operatingMode.hashCode()
        return result
    }
}
