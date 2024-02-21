package krystian.kryszczak.recruitment.service.account.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.account.employer.EmployerAccount
import krystian.kryszczak.recruitment.service.account.AccountServiceTest

@MicronautTest(transactional = false)
class EmployerServiceTest(accountService: EmployerAccountService) : AccountServiceTest<EmployerAccount>(
accountService,
arrayOf(
    EmployerAccount(
        null,
        "fsociety",
        "we are fsociety | we are finally free | we are finally awake ;^)",
        "Other",
        "Information services",
        3,
        "https://linkedin.com/company/fs0ciety",
        null,
        null,
        "https://linkedin.com/company/fs0ciety",
        null,
        null,
        null
    ), EmployerAccount(
        null,
        "Evil Corp LLC",
        "E Corp is the leading global provider of corporate strategy, philanthropy, sustainability, and growth.",
        "Corporation",
        "HTB{WW91IGNhbiBkbyB0aGlzLCBrZWVwIGdvaW5nISEh}",
        1000000,
        "https://www.e-corp-usa.com/",
        null,
        null,
        "https://www.linkedin.com/company/evil-corp-llc/",
        null,
        null,
        null
    ),
), { it.copy(name = "${it.name} - ${it.name}") },
{ item, id -> item.copy(id = id) })
