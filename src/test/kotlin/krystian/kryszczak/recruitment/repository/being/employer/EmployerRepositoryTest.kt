package krystian.kryszczak.recruitment.repository.being.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.being.employer.EmployerRepository
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.repository.being.BeingRepositoryTest

@MicronautTest(transactional = false)
class EmployerRepositoryTest(repository: EmployerRepository) : BeingRepositoryTest<Employer>(
repository,
arrayOf(
    Employer(
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
        "support@fsociety.com",
        null,
        null
    ), Employer(
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
        "support@evil-corp.com",
        null,
        null
    ),
), { it.copy(name = "${it.name} - ${it.name}") },
{ item, id -> item.copy(id = id) })
