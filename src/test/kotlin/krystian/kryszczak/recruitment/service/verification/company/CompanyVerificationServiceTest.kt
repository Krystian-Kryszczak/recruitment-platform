package krystian.kryszczak.recruitment.service.verification.company

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.service.verification.VerificationServiceTest

@MicronautTest(transactional = false)
class CompanyVerificationServiceTest(companyVerificationService: CompanyVerificationService) :
    VerificationServiceTest(companyVerificationService)
