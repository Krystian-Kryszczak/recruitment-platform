package krystian.kryszczak.recruitment.model.being.candidate

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.constant.Sex

/**
 * If any variable has a null value, it should not be changed.
 */
@Serdeable
data class CandidateUpdateForm(
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    @param:Max(1000) val messageToTheEmployer: String? = null,
    val linkedInProfile: String? = null,
    val gitHubProfile: String? = null,
    @param:Max(10) val workHistory: Array<String>? = null,
    val position: String? = null,
    @param:Max(50) val yearsOfExperience: Byte? = null,
    @param:Max(4) val categories: Array<String>? = null,
    @param:Max(12) val skills: Array<String>? = null,
    @param:Max(5) val employmentTypeAndSalary: Map<String, String>? = null,
    @param:Max(10) val locations: Array<String>? = null,
    @param:PositiveOrZero @param:Max(7) val englishLevel: Int? = null,
    val sex: Sex? = null,
    val agreeToEmailMarketing: Boolean? = null
) : BeingUpdateForm<Candidate, CandidateUpdateForm> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CandidateUpdateForm

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
        if (agreeToEmailMarketing != other.agreeToEmailMarketing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email?.hashCode() ?: 0
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (messageToTheEmployer?.hashCode() ?: 0)
        result = 31 * result + (linkedInProfile?.hashCode() ?: 0)
        result = 31 * result + (gitHubProfile?.hashCode() ?: 0)
        result = 31 * result + (workHistory?.contentHashCode() ?: 0)
        result = 31 * result + (position?.hashCode() ?: 0)
        result = 31 * result + (yearsOfExperience ?: 0)
        result = 31 * result + (categories?.contentHashCode() ?: 0)
        result = 31 * result + (skills?.contentHashCode() ?: 0)
        result = 31 * result + (employmentTypeAndSalary?.hashCode() ?: 0)
        result = 31 * result + (locations?.contentHashCode() ?: 0)
        result = 31 * result + (englishLevel ?: 0)
        result = 31 * result + (sex?.hashCode() ?: 0)
        result = 31 * result + (agreeToEmailMarketing?.hashCode() ?: 0)
        return result
    }
}
