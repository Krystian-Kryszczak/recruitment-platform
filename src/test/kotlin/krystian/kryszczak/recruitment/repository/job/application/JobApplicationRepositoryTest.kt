package krystian.kryszczak.recruitment.repository.job.application

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.application.JobApplicationRepository
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

@MicronautTest(transactional = false)
class JobApplicationRepositoryTest(repository: JobApplicationRepository) : CrudRepositoryBaseTest<JobApplication>(
repository,
arrayOf(
    JobApplication(null, uniqueId(), uniqueId(), "John", "Smith", "john.smith@gmail.com"),
    JobApplication(null, uniqueId(), uniqueId(), "Jack", "Smith", "jack.smith@gmail.com"),
    JobApplication(null, uniqueId(), uniqueId(), "Jerry", "Smith", "jerry.smith@gmail.com"),
    JobApplication(null, uniqueId(), uniqueId(), "Ariana", "Watson", "ariana.watson@gmail.com")
),
{ it.copy(offerId = "${it.offerId} - ${it.offerId}") },
{ item, id -> item.copy(id = id) })
