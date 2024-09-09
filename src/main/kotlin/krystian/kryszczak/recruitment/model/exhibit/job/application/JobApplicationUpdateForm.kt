package krystian.kryszczak.recruitment.model.exhibit.job.application

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm

@Serdeable
data class JobApplicationUpdateForm(
    val offerId: String,
    val cvFileId: String? = null,
    val messageToRecruiter: String? = null
) : ExhibitUpdateForm<JobApplication, JobApplicationUpdateForm> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobApplicationUpdateForm

        if (offerId != other.offerId) return false
        if (cvFileId != other.cvFileId) return false
        if (messageToRecruiter != other.messageToRecruiter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offerId.hashCode()
        result = 31 * result + cvFileId.hashCode()
        result = 31 * result + (messageToRecruiter?.hashCode() ?: 0)
        return result
    }
}
