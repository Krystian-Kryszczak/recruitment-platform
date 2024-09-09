package krystian.kryszczak.recruitment.service.security.credentials.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.service.AbstractDataAccessService

@Singleton
class DefaultEmployerCredentialsService(repository: EmployerCredentialsRepository) : EmployerCredentialsService,
    AbstractDataAccessService<EmployerCredentials, String>(repository)
