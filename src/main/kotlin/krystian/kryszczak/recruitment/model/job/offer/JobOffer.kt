package krystian.kryszczak.recruitment.model.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item
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
    val typeOfWork: String,
    val experience: String,
    val employmentType: String,
    val operatingMode: String,
    val earningsPerMonth: String,
    val techStack: Map<String, Byte>?,
    val places: Array<String>?,
    val recruitmentType: String,
    @DateCreated val lastModified: Instant? = null
): Item(id) {
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
        if (operatingMode != other.operatingMode) return false
        if (earningsPerMonth != other.earningsPerMonth) return false
        if (techStack != other.techStack) return false
        if (places != null) {
            if (other.places == null) return false
            if (!places.contentEquals(other.places)) return false
        } else if (other.places != null) return false
        if (recruitmentType != other.recruitmentType) return false

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
        result = 31 * result + operatingMode.hashCode()
        result = 31 * result + earningsPerMonth.hashCode()
        result = 31 * result + (techStack?.hashCode() ?: 0)
        result = 31 * result + (places?.contentHashCode() ?: 0)
        result = 31 * result + recruitmentType.hashCode()
        return result
    }
}
