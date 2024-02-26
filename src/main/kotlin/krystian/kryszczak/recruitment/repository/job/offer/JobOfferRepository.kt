package krystian.kryszczak.recruitment.repository.job.offer

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase

@MongoRepository
interface JobOfferRepository : CrudRepositoryBase<JobOffer>
