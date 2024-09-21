package krystian.kryszczak.recruitment.model.exhibit.job.application

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm
import krystian.kryszczak.recruitment.model.upload.FileUploadOrReferenceId

@Serdeable
data class JobApplicationUpdateForm(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val cvFile: FileUploadOrReferenceId? = null,
    val messageToRecruiter: String? = null
) : ExhibitUpdateForm<JobApplication, JobApplicationUpdateForm> {
    override fun extractPureTextContent(actual: JobApplication): StringBuilder = with(StringBuilder()) {
        (firstName ?: actual.firstName).let(::append)
        (lastName ?: actual.lastName).let { append(" ").append(it) }
        (email ?: actual.email).let { append(" ").append(it) }
        (messageToRecruiter ?: actual.messageToRecruiter)?.let { append(" ").append(it) }
        this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobApplicationUpdateForm

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (cvFile != other.cvFile) return false
        if (messageToRecruiter != other.messageToRecruiter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName?.hashCode() ?: 0
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (cvFile?.hashCode() ?: 0)
        result = 31 * result + (messageToRecruiter?.hashCode() ?: 0)
        return result
    }
}
