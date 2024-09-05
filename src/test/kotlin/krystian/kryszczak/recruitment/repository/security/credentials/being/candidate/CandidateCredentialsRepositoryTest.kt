package krystian.kryszczak.recruitment.repository.security.credentials.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.repository.security.credentials.being.BeingCredentialsRepositoryTest

@MicronautTest(transactional = false)
class CandidateCredentialsRepositoryTest(repository: CandidateCredentialsRepository) : BeingCredentialsRepositoryTest<CandidateCredentials>(
repository,
arrayOf(
    CandidateCredentials(null, "john@smith.com", "some-password-0"),
    CandidateCredentials(null, "jack@smith.com", "some-password-1"),
    CandidateCredentials(null, "eliot@fsociety.com", "some-password-2"),
    CandidateCredentials(null, "mr.robot@fsociety.com", "some-password-3")
), { it.copy(username = "${it.username} - ${it.username}") },
{ item, id -> item.copy(id = id) })
