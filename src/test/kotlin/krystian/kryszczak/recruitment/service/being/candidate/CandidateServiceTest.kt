package krystian.kryszczak.recruitment.service.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.constant.Sex
import krystian.kryszczak.recruitment.service.being.BeingServiceTest

@MicronautTest(transactional = false)
class CandidateServiceTest(accountService: CandidateService) : BeingServiceTest<Candidate, CandidateCreationForm, CandidateUpdateForm>(
accountService,
arrayOf(
    Candidate(
        null,
        "john.smith@example.com",
        "John",
        "Smith",
        "123 456 789",
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
        "123 456 789",
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
