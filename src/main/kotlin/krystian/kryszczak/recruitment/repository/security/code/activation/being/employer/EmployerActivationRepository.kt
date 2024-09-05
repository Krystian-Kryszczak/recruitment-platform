package krystian.kryszczak.recruitment.repository.security.code.activation.being.employer

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepository

@MongoRepository
interface EmployerActivationRepository : BeingActivationRepository<EmployerActivation>
