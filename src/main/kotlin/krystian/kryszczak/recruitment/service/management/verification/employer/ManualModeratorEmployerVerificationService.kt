package krystian.kryszczak.recruitment.service.management.verification.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.management.verification.VerificationServiceBase

@Singleton
class ManualModeratorEmployerVerificationService(employerService: EmployerService) :
    VerificationServiceBase<Employer>(employerService, { copy(verified = it) }, { verified }), EmployerVerificationService
