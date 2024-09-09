package krystian.kryszczak.recruitment.mapper.exhibit.job.application

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.mapper.exhibit.ExhibitMapperTest
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm

@MicronautTest(transactional = false)
class JobApplicationMapperTest(jobApplicationMapper: DefaultJobApplicationMapper) //:
//ExhibitMapperTest<JobApplication, JobApplicationDto, JobApplicationCreationForm, JobApplicationUpdateForm>(
//    jobApplicationMapper,
//    Triple(),
//    Triple(),
//    Pair(),
//    Pair()
//) // TODO mock
