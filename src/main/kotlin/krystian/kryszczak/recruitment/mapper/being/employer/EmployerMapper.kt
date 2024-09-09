package krystian.kryszczak.recruitment.mapper.being.employer

import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials

interface EmployerMapper : BeingMapper<Employer, EmployerDto, EmployerCreationForm, EmployerUpdateForm, EmployerCredentials, EmployerActivation>
