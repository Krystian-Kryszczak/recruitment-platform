package krystian.kryszczak.recruitment.model.exhibit.job.application

import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.exhibit.ExhibitCreationForm

@Serdeable
data class JobApplicationCreationForm(
    val offerId: String,
    val fullName: String,
    val email: String,
    val cvFile: StreamingFileUpload,
    val messageToRecruiter: String? = null
) : ExhibitCreationForm<JobApplication, JobApplicationCreationForm>
