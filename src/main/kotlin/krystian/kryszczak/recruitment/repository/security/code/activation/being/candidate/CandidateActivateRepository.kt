package krystian.kryszczak.recruitment.repository.security.code.activation.being.candidate

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepository

@MongoRepository
interface CandidateActivateRepository : BeingActivationRepository<CandidateActivation>
