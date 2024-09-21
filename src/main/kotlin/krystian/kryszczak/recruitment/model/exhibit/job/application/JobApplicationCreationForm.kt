package krystian.kryszczak.recruitment.model.exhibit.job.application

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.exhibit.ExhibitCreationForm
import krystian.kryszczak.recruitment.model.upload.FileUploadOrReferenceId

@Serdeable
data class JobApplicationCreationForm(
    val offerId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val cvFile: FileUploadOrReferenceId,
    val messageToRecruiter: String? = null
) : ExhibitCreationForm<JobApplication, JobApplicationCreationForm> {
    override fun extractPureTextContent(): StringBuilder = with(StringBuilder()) {
        append(firstName).append(" ")
        append(lastName).append(" ")
        append(email)
        messageToRecruiter?.let { append(" ").append(it) }
        this
    }
}
