package krystian.kryszczak.recruitment.service.security.registration.being.employer

import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationService

interface EmployerRegistrationService : BeingRegistrationService<Employer, EmployerCreationForm, EmployerUpdateForm, EmployerDto, EmployerCredentials, EmployerActivation>
