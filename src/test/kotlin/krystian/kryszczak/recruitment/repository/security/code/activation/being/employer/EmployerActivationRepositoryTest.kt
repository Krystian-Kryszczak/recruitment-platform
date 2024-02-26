package krystian.kryszczak.recruitment.repository.security.code.activation.being.employer

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepositoryTest

@MicronautTest(transactional = false)
class EmployerActivationRepositoryTest(repository: EmployerActivationRepository) : BeingActivationRepositoryTest<EmployerActivation>(
repository,
arrayOf(
    EmployerActivation(null, uniqueId(), "john@e-corp.com", EmployerFormation("john@e-corp.com", "john", "smith"), "ep-0"),
    EmployerActivation(null, uniqueId(), "jack@e-corp.com", EmployerFormation("jack@e-corp.com", "jack", "smith"), "ep-1"),
    EmployerActivation(null, uniqueId(), "alex@e-corp.com", EmployerFormation("alex@e-corp.com", "alex", "smith"), "ep-2"),
    EmployerActivation(null, uniqueId(), "levi@e-corp.com", EmployerFormation("levi@e-corp.com", "levi", "smith"), "ep-3")
),
{ it.copy(identity = "${it.identity} - ${it.identity}") },
{ item, id -> item.copy(id = id) })
