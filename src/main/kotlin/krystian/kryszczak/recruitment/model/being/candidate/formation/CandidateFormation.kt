package krystian.kryszczak.recruitment.model.being.candidate.formation

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.candidate.Candidate

@Serdeable
@Introspected
data class CandidateFormation(
    val email: String,
    val firstName: String,
    val lastName: String,
    @param:Max(1000) val messageToTheEmployer: String? = null,
    val linkedInProfile: String? = null,
    val gitHubProfile: String? = null,
    @param:Max(10) val workHistory: Array<String>? = null,
    val position: String? = null,
    @param:Max(50) val yearsOfExperience: Byte = 0,
    @param:Max(4) val categories: Array<String>? = null,
    @param:Max(12) val skills: Array<String>? = null,
    @param:Max(5) val employmentTypeAndSalary: Array<String>? = null,
    @param:Max(10) val locations: Array<String>? = null,
    @param:PositiveOrZero @param:Max(7) val englishLevel: Int = 0,
    val sex: Boolean? = null,
    val agreeToEmailMarketing: Boolean = false
): Formation<Candidate>() {
    override fun format(id: String?): Candidate = Candidate(
        id,
        email,
        firstName,
        lastName,
        messageToTheEmployer,
        linkedInProfile,
        gitHubProfile,
        workHistory,
        position,
        yearsOfExperience,
        categories,
        skills,
        employmentTypeAndSalary,
        locations,
        englishLevel,
        sex,
        agreeToEmailMarketing
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CandidateFormation

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
        if (agreeToEmailMarketing != other.agreeToEmailMarketing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + (messageToTheEmployer?.hashCode() ?: 0)
        result = 31 * result + (linkedInProfile?.hashCode() ?: 0)
        result = 31 * result + (gitHubProfile?.hashCode() ?: 0)
        result = 31 * result + (workHistory?.contentHashCode() ?: 0)
        result = 31 * result + (position?.hashCode() ?: 0)
        result = 31 * result + yearsOfExperience
        result = 31 * result + (categories?.contentHashCode() ?: 0)
        result = 31 * result + (skills?.contentHashCode() ?: 0)
        result = 31 * result + (employmentTypeAndSalary?.contentHashCode() ?: 0)
        result = 31 * result + (locations?.contentHashCode() ?: 0)
        result = 31 * result + englishLevel
        result = 31 * result + (sex?.hashCode() ?: 0)
        result = 31 * result + agreeToEmailMarketing.hashCode()
        return result
    }
}
