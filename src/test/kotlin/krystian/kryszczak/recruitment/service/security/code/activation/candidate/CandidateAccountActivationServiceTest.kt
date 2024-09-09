package krystian.kryszczak.recruitment.service.security.code.activation.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.service.security.code.activation.AccountActivationServiceTest
import krystian.kryszczak.recruitment.service.security.code.activation.account.candidate.CandidateAccountActivationService

@MicronautTest(transactional = false)
class CandidateAccountActivationServiceTest(accountActivationService: CandidateAccountActivationService) :
    AccountActivationServiceTest(accountActivationService)
