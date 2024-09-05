package krystian.kryszczak.recruitment.repository.being.candidate

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.repository.being.BeingRepository

@MongoRepository
interface CandidateRepository : BeingRepository<Candidate>
