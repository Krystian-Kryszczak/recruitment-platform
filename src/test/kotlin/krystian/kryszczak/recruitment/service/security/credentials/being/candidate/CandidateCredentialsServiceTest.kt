package krystian.kryszczak.recruitment.service.security.credentials.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Named
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsService
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsServiceTest

@MicronautTest(transactional = false)
class CandidateCredentialsServiceTest(@Named("candidate") credentialsService: CredentialsService<CandidateCredentials>) :
    CredentialsServiceTest<CandidateCredentials>(credentialsService)
