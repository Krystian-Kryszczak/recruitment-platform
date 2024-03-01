package krystian.kryszczak.recruitment.repository.security.code.activation.being.candidate

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepositoryTest

@MicronautTest(transactional = false)
class CandidateActivationRepositoryTest(repository: CandidateActivationRepository) : BeingActivationRepositoryTest<CandidateActivation>(
repository,
arrayOf(
    CandidateActivation(null, uniqueId(), "john@smith.com",
        CandidateCreationForm("john@smith.com", "john", "smith",
            password = uniqueId(), acceptRules = true),
        "ep-0"),
    CandidateActivation(null, uniqueId(), "jack@smith.com",
        CandidateCreationForm("jack@smith.com", "jack", "smith",
            password = uniqueId(), acceptRules = true),
        "ep-1"),
    CandidateActivation(null, uniqueId(), "alex@smith.com",
        CandidateCreationForm("alex@smith.com", "alex", "smith",
            password = uniqueId(), acceptRules = true),
        "ep-2"),
    CandidateActivation(null, uniqueId(), "levi@smith.com",
        CandidateCreationForm("levi@smith.com", "levi", "smith",
            password = uniqueId(), acceptRules = true),
        "ep-3")
), { it.copy(identity = "${it.identity} - ${it.identity}") },
{ item, id -> item.copy(id = id) })
