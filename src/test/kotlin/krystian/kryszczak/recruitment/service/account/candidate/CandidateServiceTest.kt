package krystian.kryszczak.recruitment.service.account.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.account.candidate.CandidateAccount
import krystian.kryszczak.recruitment.service.account.AccountServiceTest

@MicronautTest(transactional = false)
class CandidateServiceTest(accountService: CandidateAccountService) : AccountServiceTest<CandidateAccount>(
accountService,
arrayOf(
    CandidateAccount(
        null,
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
        true
    ), CandidateAccount(
        null,
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
        true
    )
), { it.copy(firstName = it.firstName + "-" + it.lastName) },
{ item, id -> item.copy(id = id) })
