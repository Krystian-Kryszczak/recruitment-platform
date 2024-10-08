package krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.employer

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository

@MongoRepository
interface EmployerCredentialsRepository : BeingCredentialsRepository<EmployerCredentials>
