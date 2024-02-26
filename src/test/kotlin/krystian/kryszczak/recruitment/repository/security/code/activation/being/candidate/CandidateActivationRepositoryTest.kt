package krystian.kryszczak.recruitment.repository.security.code.activation.being.candidate

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.being.BeingActivationRepositoryTest

@MicronautTest(transactional = false)
class CandidateActivationRepositoryTest(repository: CandidateActivateRepository) : BeingActivationRepositoryTest<CandidateActivation>(
repository,
arrayOf(
    CandidateActivation(null, uniqueId(), "john@smith.com", CandidateFormation("john@smith.com", "john", "smith"), "ep-0"),
    CandidateActivation(null, uniqueId(), "jack@smith.com", CandidateFormation("jack@smith.com", "jack", "smith"), "ep-1"),
    CandidateActivation(null, uniqueId(), "alex@smith.com", CandidateFormation("alex@smith.com", "alex", "smith"), "ep-2"),
    CandidateActivation(null, uniqueId(), "levi@smith.com", CandidateFormation("levi@smith.com", "levi", "smith"), "ep-3")
),
{ it.copy(identity = "${it.identity} - ${it.identity}") },
{ item, id -> item.copy(id = id) })
