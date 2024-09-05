package krystian.kryszczak.recruitment.repository.job.application

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.job.application.JobApplication
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase

@MongoRepository
interface JobApplicationRepository : CrudRepositoryBase<JobApplication>
