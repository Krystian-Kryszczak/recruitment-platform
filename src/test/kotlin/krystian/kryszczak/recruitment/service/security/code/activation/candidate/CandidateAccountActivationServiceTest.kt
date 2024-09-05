package krystian.kryszczak.recruitment.service.security.code.activation.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Named
import krystian.kryszczak.recruitment.service.security.code.activation.AccountActivationServiceTest
import krystian.kryszczak.recruitment.service.security.code.activation.account.AccountActivationService

@MicronautTest(transactional = false)
class CandidateAccountActivationServiceTest(@Named("candidate") accountActivationService: AccountActivationService) :
    AccountActivationServiceTest(accountActivationService)
