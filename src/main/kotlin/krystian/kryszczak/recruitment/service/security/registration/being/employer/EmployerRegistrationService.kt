package krystian.kryszczak.recruitment.service.security.registration.being.employer

import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import krystian.kryszczak.recruitment.service.security.registration.RegistrationService

interface EmployerRegistrationService : RegistrationService<Employer, EmployerFormation>
