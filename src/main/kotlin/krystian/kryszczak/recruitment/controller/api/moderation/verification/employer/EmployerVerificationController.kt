package krystian.kryszczak.recruitment.controller.api.moderation.verification.employer

import io.micronaut.http.annotation.Controller
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.moderation.verification.VerificationController
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.service.management.verification.employer.EmployerVerificationService

@RolesAllowed("ADMIN")
@Controller("api/v1/moderation/verification/")
class EmployerVerificationController(verificationService: EmployerVerificationService) :
    VerificationController<Employer, String>(verificationService)
