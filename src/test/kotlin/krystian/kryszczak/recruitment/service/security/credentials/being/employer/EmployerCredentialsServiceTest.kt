package krystian.kryszczak.recruitment.service.security.credentials.being.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Named
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsServiceTest

@MicronautTest(transactional = false)
class EmployerCredentialsServiceTest(@Named("employer") credentialsService: CredentialsService<EmployerCredentials>) :
    CredentialsServiceTest<EmployerCredentials>(credentialsService)
