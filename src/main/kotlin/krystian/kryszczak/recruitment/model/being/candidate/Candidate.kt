package krystian.kryszczak.recruitment.model.being.candidate

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.being.Being

@Serdeable
@MappedEntity
@Introspected
data class Candidate(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    @param:Max(1000) val messageToTheEmployer: String,
    val linkedInProfile: String,
    val gitHubProfile: String,
    @param:Max(10) val workHistory: Array<String>?,
    val position: String,
    @param:Max(50) val yearsOfExperience: Byte,
    @param:Max(4) val categories: Array<String>?,
    @param:Max(12) val skills: Array<String>?,
    @param:Max(5) val employmentTypeAndSalary: Array<String>?,
    @param:Max(10) val locations: Array<String>?,
    @param:PositiveOrZero @param:Max(7) val englishLevel: Int,
    val sex: Boolean
) : Being(id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Candidate

        if (id != other.id) return false
        if (email != other.email) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (messageToTheEmployer != other.messageToTheEmployer) return false
        if (linkedInProfile != other.linkedInProfile) return false
        if (gitHubProfile != other.gitHubProfile) return false
        if (workHistory != null) {
            if (other.workHistory == null) return false
            if (!workHistory.contentEquals(other.workHistory)) return false
        } else if (other.workHistory != null) return false
        if (position != other.position) return false
        if (yearsOfExperience != other.yearsOfExperience) return false
        if (categories != null) {
            if (other.categories == null) return false
            if (!categories.contentEquals(other.categories)) return false
        } else if (other.categories != null) return false
        if (skills != null) {
            if (other.skills == null) return false
            if (!skills.contentEquals(other.skills)) return false
        } else if (other.skills != null) return false
        if (employmentTypeAndSalary != null) {
            if (other.employmentTypeAndSalary == null) return false
            if (!employmentTypeAndSalary.contentEquals(other.employmentTypeAndSalary)) return false
        } else if (other.employmentTypeAndSalary != null) return false
        if (locations != null) {
            if (other.locations == null) return false
            if (!locations.contentEquals(other.locations)) return false
        } else if (other.locations != null) return false
        if (englishLevel != other.englishLevel) return false
        if (sex != other.sex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + messageToTheEmployer.hashCode()
        result = 31 * result + linkedInProfile.hashCode()
        result = 31 * result + gitHubProfile.hashCode()
        result = 31 * result + (workHistory?.contentHashCode() ?: 0)
        result = 31 * result + position.hashCode()
        result = 31 * result + yearsOfExperience
        result = 31 * result + (categories?.contentHashCode() ?: 0)
        result = 31 * result + (skills?.contentHashCode() ?: 0)
        result = 31 * result + (employmentTypeAndSalary?.contentHashCode() ?: 0)
        result = 31 * result + (locations?.contentHashCode() ?: 0)
        result = 31 * result + englishLevel
        result = 31 * result + sex.hashCode()
        return result
    }
}
