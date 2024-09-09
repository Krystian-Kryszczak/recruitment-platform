package krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.candidate

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository

@MongoRepository
interface CandidateCredentialsRepository : BeingCredentialsRepository<CandidateCredentials>
