package krystian.kryszczak.recruitment.database.mongodb.repository.being.candidate

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.database.mongodb.repository.being.BeingRepository

@MongoRepository
interface CandidateRepository : BeingRepository<Candidate>
