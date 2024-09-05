package krystian.kryszczak.recruitment.repository.security.credentials.being.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.repository.security.credentials.being.BeingCredentialsRepositoryTest

@MicronautTest(transactional = false)
class EmployerCredentialsRepositoryTest(repository: EmployerCredentialsRepository) : BeingCredentialsRepositoryTest<EmployerCredentials>(
repository,
arrayOf(
    EmployerCredentials(null, "philp.price@e-corp.com", "some-password-0"),
    EmployerCredentials(null, "angela.moss@e-corp.com", "some-password-1"),
    EmployerCredentials(null, "tyrell.wellick@e-corp.com", "some-password-2"),
    EmployerCredentials(null, "terry.colby@e-corp.com", "some-password-3")
), { it.copy(username = "${it.username} - ${it.username}") },
{ item, id -> item.copy(id = id) })
