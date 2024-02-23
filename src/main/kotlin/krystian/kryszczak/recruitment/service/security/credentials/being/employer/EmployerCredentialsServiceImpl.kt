package krystian.kryszczak.recruitment.service.security.credentials.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl

@Singleton
class EmployerCredentialsServiceImpl(repository: EmployerCredentialsRepository) :
    EmployerCredentialsService, DataAccessServiceImpl<EmployerCredentials>(repository)
