package krystian.kryszczak.recruitment.service.management.verification.employer

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.management.verification.VerificationServiceTest
import krystian.kryszczak.test.mock.employerMock

@MicronautTest(transactional = false)
class EmployerVerificationServiceTest(verificationService: EmployerVerificationService) : VerificationServiceTest<Employer>(
    verificationService,
    employerMock,
    { copy(verified = it) }
) {
    @MockBean(EmployerService::class)
    fun employerService() = dataAccessService<Employer, EmployerService>(employerMock)
}
