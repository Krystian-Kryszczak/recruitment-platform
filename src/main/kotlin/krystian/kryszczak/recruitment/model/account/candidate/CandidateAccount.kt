package krystian.kryszczak.recruitment.model.account.candidate

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.account.Account

@Serdeable
@MappedEntity
@Introspected
data class CandidateAccount(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val firstName: String,
    val lastName: String,
    @param:Max(1000) val messageToTheEmployer: String,
    val linkedInProfile: String,
    val gitHubProfile: String,
    @param:Max(10) val workHistory: Array<String>?,
    val position: String,
    val yearsOfExperience: Byte,
    @param:Max(4) val categories: Array<String>?,
    @param:Max(12) val skills: Array<String>?,
    @param:Max(5) val employmentTypeAndSalary: Array<String>?,
    @param:Max(10) val locations: Array<String>?,
    @param:PositiveOrZero @param:Max(7) val englishLevel: Int
) : Account(id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CandidateAccount

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (messageToTheEmployer != other.messageToTheEmployer) return false
        if (linkedInProfile != other.linkedInProfile) return false
        if (gitHubProfile != other.gitHubProfile) return false
        if (!workHistory.contentEquals(other.workHistory)) return false
        if (position != other.position) return false
        if (yearsOfExperience != other.yearsOfExperience) return false
        if (!categories.contentEquals(other.categories)) return false
        if (!skills.contentEquals(other.skills)) return false
        if (!employmentTypeAndSalary.contentEquals(other.employmentTypeAndSalary)) return false
        if (!locations.contentEquals(other.locations)) return false
        if (englishLevel != other.englishLevel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + messageToTheEmployer.hashCode()
        result = 31 * result + linkedInProfile.hashCode()
        result = 31 * result + gitHubProfile.hashCode()
        result = 31 * result + workHistory.contentHashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + yearsOfExperience
        result = 31 * result + categories.contentHashCode()
        result = 31 * result + skills.contentHashCode()
        result = 31 * result + employmentTypeAndSalary.contentHashCode()
        result = 31 * result + locations.contentHashCode()
        result = 31 * result + englishLevel
        return result
    }
}
