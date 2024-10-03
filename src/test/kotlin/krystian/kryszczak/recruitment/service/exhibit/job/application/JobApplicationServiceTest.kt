package krystian.kryszczak.recruitment.service.exhibit.job.application

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.mapper.exhibit.job.application.JobApplicationMapper
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.exhibit.ExhibitServiceTest
import krystian.kryszczak.test.mock.candidateMock
import krystian.kryszczak.test.mock.jobOfferMock
import reactor.core.publisher.Mono
import java.time.Instant

@MicronautTest(transactional = false)
class JobApplicationServiceTest(jobApplicationService: JobApplicationService) :
ExhibitServiceTest<JobApplication, JobApplicationUpdateForm> (
jobApplicationService,
arrayOf(
    JobApplication(
        null,
        jobOfferMock.id!!,
        candidateMock.id!!,
        "John",
        "Smith",
        "john.smith@gmail.com",
        "What's up?",
        null,
        false,
        Instant.now()
    ), JobApplication(
        null,
        jobOfferMock.id!!,
        candidateMock.id!!,
        "Eliot",
        "Alderson",
        "hello.friend@gmail.com",
        "Hello there!",
        null,
        false,
        Instant.now()
    )
),
{ it.copy(messageToRecruiter = "${it.messageToRecruiter} Update.") },
{ item, id -> item.copy(id = id) }) {
    @MockBean(JobApplicationMapper::class)
    fun jobApplicationMapper(): JobApplicationMapper {
        return mockk {
            every { mapToOriginItem(any<JobApplicationCreationForm>(), any()) } returns Mono.just(
                JobApplication(
                    null,
                    jobOfferMock.id!!,
                    candidateMock.id!!,
                    "John",
                    "Smith",
                    "<EMAIL>",
                    "What's up?",
                    null
                )
            )
        }
    }
}
