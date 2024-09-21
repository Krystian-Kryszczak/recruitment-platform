package krystian.kryszczak.recruitment.service.exhibit.job.application

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.exhibit.ExhibitServiceTest
import krystian.kryszczak.test.mock.candidateMock
import krystian.kryszczak.test.mock.jobOfferMock
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
{ item, id -> item.copy(id = id) }, {
    "job application service test" - { // TODO
        "find by id for employer client" {
            //
        }

        "find by offer id for employer client" {
            //
        }

        "find published by candidate client" {
            //
        }

        "delete own by id for candidate client" {
            //
        }
    }
})
