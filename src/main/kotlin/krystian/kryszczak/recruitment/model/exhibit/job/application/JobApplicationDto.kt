package krystian.kryszczak.recruitment.model.exhibit.job.application

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.exhibit.ExhibitDto
import java.time.Instant

@Serdeable
data class JobApplicationDto(
    val id: String? = null,
    val offerId: String,
    val candidateId: String,
    val cvFileId: String,
    val messageToRecruiter: String? = null,
    val dateCreated: Instant? = null
) : ExhibitDto<JobApplication, JobApplicationDto>
