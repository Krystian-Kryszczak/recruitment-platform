package krystian.kryszczak.recruitment.service.security.code.activation.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.service.security.code.activation.AccountActivationServiceTest
import krystian.kryszczak.recruitment.service.security.code.activation.account.employer.EmployerAccountActivationService

@MicronautTest(transactional = false)
class EmployerAccountActivationServiceTest(accountActivationService: EmployerAccountActivationService) :
    AccountActivationServiceTest(accountActivationService)
