package krystian.kryszczak.recruitment.service.security.credentials.being.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsServiceTest

@MicronautTest(transactional = false)
class EmployerCredentialsServiceTest(credentialsService: EmployerCredentialsService) :
CredentialsServiceTest<EmployerCredentials>(
    credentialsService,
    arrayOf(
        EmployerCredentials(
            null,
            "Elliot Alderson",
            "8iZC79S4oq"
        ),
        EmployerCredentials(
            null,
            "Darlene Alderson",
            "A28oRrPqOu"
        ),
        EmployerCredentials(
            null,
            "Tyrell Wellick",
            "cn1O46wKZV"
        ),
        EmployerCredentials(
            null,
            "Phillip Price",
            "Be6ak1wBfM"
        )
    ),
    { it.copy(username = "${it.username}-(updated)") },
    { item, id -> item.copy(id = id) }
)
