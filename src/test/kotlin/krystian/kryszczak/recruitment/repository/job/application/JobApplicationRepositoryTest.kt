package krystian.kryszczak.recruitment.repository.job.application

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.job.application.JobApplication
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

@MicronautTest(transactional = false)
class JobApplicationRepositoryTest(repository: JobApplicationRepository) : CrudRepositoryBaseTest<JobApplication>(
repository,
arrayOf(
    JobApplication(null, uniqueId(), uniqueId()),
    JobApplication(null, uniqueId(), uniqueId()),
    JobApplication(null, uniqueId(), uniqueId()),
    JobApplication(null, uniqueId(), uniqueId()),
),
{ it.copy(offerId = "${it.offerId} - ${it.offerId}") },
{ item, id -> item.copy(id = id) })
