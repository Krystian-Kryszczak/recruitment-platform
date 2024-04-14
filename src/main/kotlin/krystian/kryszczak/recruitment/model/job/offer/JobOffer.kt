package krystian.kryszczak.recruitment.model.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.job.Exhibit
import java.time.Instant

@Serdeable
@MappedEntity
@Introspected
data class JobOffer(
    @field:Id @GeneratedValue override val id: String? = null,
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
    val path: String, // TODO change to list or add old links val that will be use full after edit
    val banned: Boolean = false,
    @DateCreated val dateCreated: Instant? = null
) : Exhibit(id, banned) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobOffer

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
        if (expires.epochSecond != other.expires.epochSecond) return false
        if (path != other.path) return false
        if (banned != other.banned) return false

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
        result = 31 * result + banned.hashCode()
        return result
    }
}
