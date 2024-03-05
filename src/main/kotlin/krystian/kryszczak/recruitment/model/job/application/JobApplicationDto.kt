package krystian.kryszczak.recruitment.model.job.application

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Dto
import java.time.Instant

@Serdeable
@Introspected
data class JobApplicationDto(
    val id: String? = null,
    val offerId: String,
    val candidateId: String,
    val cvFileId: String,
    val messageToRecruiter: String? = null,
    val dateCreated: Instant? = null
) : Dto<JobApplication, JobApplicationDto> {
    companion object : Dto.Mapper<JobApplication, JobApplicationDto> {
        override fun from(item: JobApplication) = JobApplicationDto(
            item.id,
            item.offerId,
            item.candidateId,
            item.cvFileId,
            item.messageToRecruiter,
            item.dateCreated
        )
    }
}
