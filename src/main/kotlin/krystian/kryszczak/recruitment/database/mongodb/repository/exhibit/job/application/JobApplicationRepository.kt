package krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.application

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.ExhibitRepository

@MongoRepository
interface JobApplicationRepository : ExhibitRepository<JobApplication>
