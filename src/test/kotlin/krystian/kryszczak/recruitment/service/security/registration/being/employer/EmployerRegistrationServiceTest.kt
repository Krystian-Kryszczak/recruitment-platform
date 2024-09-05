package krystian.kryszczak.recruitment.service.security.registration.being.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Named
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.service.security.registration.RegistrationService
import krystian.kryszczak.recruitment.service.security.registration.RegistrationServiceTest

@MicronautTest(transactional = false)
class EmployerRegistrationServiceTest(@Named("employer") registrationService: RegistrationService<Employer, EmployerCreationForm>) :
    RegistrationServiceTest<Employer, EmployerCreationForm>(registrationService)
