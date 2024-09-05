package krystian.kryszczak.recruitment.model.job.application

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.multipart.CompletedFileUpload
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse

@Serdeable
@Introspected
data class JobApplicationCreationForm(
    val offerId: String,
    val fullName: String,
    val email: String,
    val cvFile: CompletedFileUpload,
    val messageToRecruiter: String? = null
) : CreationForm<JobApplication, JobApplicationCreationForm> {
    override fun transform(metadata: Map<String, Any>) =
        (metadata["moderation"] as ModerationResponse?).let { moderation ->
            JobApplication(
                null,
                offerId,
                metadata["candidateId"] as String,
                metadata["cvFileId"] as String,
                messageToRecruiter,
                moderation,
                moderation?.result?.flagged ?: false
            )
        }
}
