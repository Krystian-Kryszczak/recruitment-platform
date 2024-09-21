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
import krystian.kryszczak.recruitment.model.upload.FileUploadOrReferenceId
import krystian.kryszczak.recruitment.service.blob.BlobStorageService
import krystian.kryszczak.recruitment.service.management.moderation.ModerationService
import krystian.kryszczak.recruitment.service.text.TextExtractionService
import krystian.kryszczak.test.mock.candidateMock
import krystian.kryszczak.test.mock.jobOfferMock
import reactor.core.publisher.Mono
import java.io.FileInputStream
import java.io.InputStream

@MicronautTest(transactional = false)
class JobApplicationMapperTest(jobApplicationMapper: JobApplicationMapper) :
ExhibitMapperTest<JobApplication, JobApplicationDto, JobApplicationCreationForm, JobApplicationUpdateForm>(
    jobApplicationMapper,
    Triple(
        JobApplicationCreationForm(
            jobOfferMock.id!!,
            "John",
            "Smith",
            "john.smith@gmail.com",
            FileUploadOrReferenceId(
                mockk<StreamingFileUpload> {
                    every { asInputStream() } returns FileInputStream("src/test/resources/assets/pdf/Hello_world!.pdf")
                    every { filename } returns "Hello_world!.pdf"
                }
            ),
            "Hello there!"
        ),
        "<doer-id>",
        JobApplication(
            "<doer-id>",
            jobOfferMock.id!!,
            "<doer-id>",
            "John",
            "Smith",
            "john.smith@gmai.com",
            "Hello there!",
            null,
            false,
            null
        )
    ),
    Triple(
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "John",
            "Smith",
            "john.smith@gmail.com",
            "Hello there!",
            null,
            false,
            null
        ),
        JobApplicationUpdateForm(
            "John",
            "Smith",
            "john.smith@gmail.com",
            messageToRecruiter = "What's up?",
        ),
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "John",
            "Smith",
            "john.smith@gmail.com",
            "What's up?",
            null,
            false,
            null
        )
    ),
    Pair(
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "John",
            "Smith",
            "john.smith@gmail.com",
            "What's up?",
            null,
            false
        ),
        JobApplicationDto(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "John",
            "Smith",
            "john.smith@gmail.com",
            "What's up?",
            null,
        )
    ),
    Pair(
        JobApplication(
            "<job-application-id>",
            jobOfferMock.id!!,
            candidateMock.id!!,
            "John",
            "Smith",
            "john.smith@gmail.com",
            "What's up?",
            null,
        ),
        JobApplicationUpdateForm(
            jobOfferMock.id!!,
            "<cv-file-id>",
            "Hello there!"
        )
    ),
    { item, id -> item.copy(id = id) }
) {
    @MockBean(BlobStorageService::class)
    fun blobStorageService(): BlobStorageService {
        return mockk<BlobStorageService> {
            every { save(any(), any(), any()) } returns Mono.just("<mockk-cv-file-id>")
        }
    }

    @MockBean(TextExtractionService::class)
    fun textExtractionService(): TextExtractionService {
        return mockk<TextExtractionService> {
            every { extractText(any<InputStream>(), any()) } returns Mono.just("Hello world!\n")
        }
    }

    @MockBean(ModerationService::class)
    fun moderationService(): ModerationService {
        return mockk<ModerationService> {
            every { checkIfInputIsHarmful(any(), any()) } returns Mono.just(false)
        }
    }
}
