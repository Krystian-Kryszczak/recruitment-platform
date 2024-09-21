package krystian.kryszczak.recruitment.repository.security.code.activation.being.employer

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.employer.EmployerActivationRepository
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepositoryTest

@MicronautTest(transactional = false)
class EmployerActivationRepositoryTest(repository: EmployerActivationRepository) :
    BeingActivationRepositoryTest<EmployerActivation>(
repository,
arrayOf(
    EmployerActivation(null, uniqueId(), "john@e-corp.com",
        EmployerCreationForm("john@e-corp.com", "john", "smith",
            password = uniqueId(), country = "USA", acceptRules = true),
        "ep-0"),
    EmployerActivation(null, uniqueId(), "jack@e-corp.com",
        EmployerCreationForm("jack@e-corp.com", "jack", "smith",
            password = uniqueId(), country = "USA", acceptRules = true),
        "ep-1"),
    EmployerActivation(null, uniqueId(), "alex@e-corp.com",
        EmployerCreationForm("alex@e-corp.com", "alex", "smith",
            password = uniqueId(), country = "USA", acceptRules = true),
        "ep-2"),
    EmployerActivation(null, uniqueId(), "levi@e-corp.com",
        EmployerCreationForm("levi@e-corp.com", "levi", "smith",
            password = uniqueId(), country = "USA", acceptRules = true),
        "ep-3")
), { it.copy(identity = "${it.identity} - ${it.identity}") },
{ item, id -> item.copy(id = id) })
