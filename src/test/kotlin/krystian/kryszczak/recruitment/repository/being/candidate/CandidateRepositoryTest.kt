package krystian.kryszczak.recruitment.repository.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.being.candidate.CandidateRepository
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.constant.Sex
import krystian.kryszczak.recruitment.repository.being.BeingRepositoryTest

@MicronautTest(transactional = false)
class CandidateRepositoryTest(candidateRepository: CandidateRepository, ) : BeingRepositoryTest<Candidate>(
candidateRepository,
arrayOf(
    Candidate(
        null,
        "john.smith@example.com",
        "John",
        "Smith",
        "Hello world!",
        "https://www.linkedin.com/",
        "https://github.com/",
        null,
        "Backend Developer",
        5,
        null,
        null,
        null,
        null,
        6,
        Sex.MALE
    ), Candidate(
        null,
        "jack.smith@example.com",
        "Jack",
        "Smith",
        "Hello world!",
        "https://www.linkedin.com/",
        "https://github.com/",
        null,
        "Backend Developer",
        5,
        null,
        null,
        null,
        null,
        6,
        Sex.MALE
    )
), { it.copy(firstName = it.firstName + "-" + it.lastName) },
{ item, id -> item.copy(id = id) })
