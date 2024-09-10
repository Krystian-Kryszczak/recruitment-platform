package krystian.kryszczak.recruitment.mapper.exhibit.job.application

import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.mapper.exhibit.ExhibitMapperTest
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.blob.BlobStorageService
import krystian.kryszczak.recruitment.service.moderation.ModerationService
import krystian.kryszczak.test.mock.candidateMock
import krystian.kryszczak.test.mock.jobOfferMock
import reactor.core.publisher.Mono
import java.io.File
import java.time.Instant

@MicronautTest(transactional = false)
class JobApplicationMapperTest(jobApplicationMapper: JobApplicationMapper) :
ExhibitMapperTest<JobApplication, JobApplicationDto, JobApplicationCreationForm, JobApplicationUpdateForm>(
    jobApplicationMapper,
    Triple(
        JobApplicationCreationForm(
            jobOfferMock.id!!,
            "John Smith",
            "john.smith@gmail.com",
            mockk<StreamingFileUpload> {
                every { transferTo(any<File>()) }
            },
            "Hello there!"
        ),
        "<doer-id>",
        JobApplication(
            "<doer-id>",
            jobOfferMock.id!!,
            "<doer-id>",
            "<mockk-cv-file-id>",
            "Hello there!",
            false,
            null
        )
    ),
    Triple(
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "<cv-file-id>",
            "Hello there!",
            false,
            null
        ),
        JobApplicationUpdateForm(
            "<job-application-id>",
            messageToRecruiter = "What's up?",
        ),
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "<cv-file-id>",
            messageToRecruiter = "What's up?",
            false,
            null
        )
    ),
    Pair(
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "<cv-file-id>",
            "Hello there!",
            false,
            null
        ),
        JobApplicationDto(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "<cv-file-id>",
            "Hello there!",
            null
        )
    ),
    Pair(
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "<cv-file-id>",
            "Hello there!",
            false,
            Instant.now()
        ),
        JobApplicationUpdateForm(
            jobOfferMock.id!!,
            "<cv-file-id>",
            "Hello there!"
        )
    ),
    { item, id -> item.copy(id = id) }
) {
    @MockBean(ModerationService::class)
    fun moderationService(): ModerationService {
        return mockk<ModerationService> {
            every { checkIfInputIsHarmful(any(), any()) } returns Mono.just(false)
        }
    }

    @MockBean(BlobStorageService::class)
    fun blobStorageService(): BlobStorageService {
        return mockk<BlobStorageService> {
            every { save(any(), any(), any()) } returns Mono.just("<mockk-cv-file-id>")
        }
    }
}
