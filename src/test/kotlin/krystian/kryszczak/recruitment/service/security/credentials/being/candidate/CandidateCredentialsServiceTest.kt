package krystian.kryszczak.recruitment.service.security.credentials.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsServiceTest

@MicronautTest(transactional = false)
class CandidateCredentialsServiceTest(credentialsService: CandidateCredentialsService) :
CredentialsServiceTest<CandidateCredentials>(
    credentialsService,
    arrayOf(
        CandidateCredentials(
            null,
            "John Smith",
            "61XtS3H3Do"
        ),
        CandidateCredentials(
            null,
            "Christian Smith",
            "OD8el26gDu"
        ),
        CandidateCredentials(
            null,
            "Jerry Smith",
            "E0pNe3gOYF"
        ),
        CandidateCredentials(
            null,
            "Ellie Smith",
            "nmWg5F2kKk"
        )
    ),
    { it.copy(username = "${it.username}-(updated)") },
    { item, id -> item.copy(id = id) }
)
