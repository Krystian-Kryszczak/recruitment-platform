package krystian.kryszczak.recruitment.model.being.candidate

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.constant.Sex

@Serdeable
data class CandidateDto(
    val id: String? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val messageToTheEmployer: String? = null,
    val linkedInProfile: String? = null,
    val gitHubProfile: String? = null,
    val workHistory: Array<String>? = null,
    val position: String? = null,
    val yearsOfExperience: Byte = 0,
    val categories: Array<String>? = null,
    val skills: Array<String>? = null,
    val employmentTypeAndSalary: Map<String, String>? = null,
    val locations: Array<String>? = null,
    val englishLevel: Int = 0,
    val sex: Sex? = null
) : BeingDto<Candidate, CandidateDto> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CandidateDto

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
        if (employmentTypeAndSalary != other.employmentTypeAndSalary) return false
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
        result = 31 * result + (messageToTheEmployer?.hashCode() ?: 0)
        result = 31 * result + (linkedInProfile?.hashCode() ?: 0)
        result = 31 * result + (gitHubProfile?.hashCode() ?: 0)
        result = 31 * result + (workHistory?.contentHashCode() ?: 0)
        result = 31 * result + (position?.hashCode() ?: 0)
        result = 31 * result + yearsOfExperience
        result = 31 * result + (categories?.contentHashCode() ?: 0)
        result = 31 * result + (skills?.contentHashCode() ?: 0)
        result = 31 * result + (employmentTypeAndSalary?.hashCode() ?: 0)
        result = 31 * result + (locations?.contentHashCode() ?: 0)
        result = 31 * result + englishLevel
        result = 31 * result + (sex?.hashCode() ?: 0)
        return result
    }
}
