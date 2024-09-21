package krystian.kryszczak.recruitment.service.security.registration.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.employer.EmployerActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.employer.EmployerMapper
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationServiceBase

@Singleton
class DefaultEmployerRegistrationService(
    activationRepository: EmployerActivationRepository,
    credentialsRepository: EmployerCredentialsRepository,
    employerService: EmployerService,
    employerMapper: EmployerMapper,
    smtpMailerService: SmtpMailerService
) : BeingRegistrationServiceBase<Employer, EmployerCreationForm, EmployerUpdateForm, EmployerDto, EmployerCredentials, EmployerActivation>(
    activationRepository, credentialsRepository, employerService, employerMapper, smtpMailerService
), EmployerRegistrationService
