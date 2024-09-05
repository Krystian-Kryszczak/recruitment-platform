package krystian.kryszczak.recruitment.service.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.repository.being.employer.EmployerRepository
import krystian.kryszczak.recruitment.repository.security.code.activation.being.employer.EmployerActivationRepository
import krystian.kryszczak.recruitment.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.service.being.BeingServiceBase
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService

@Singleton
class EmployerServiceImpl(
    repository: EmployerRepository,
    passwordEncoder: PasswordEncoder,
    smtpMailerService: SmtpMailerService,
    activationRepository: EmployerActivationRepository,
    credentialsRepository: EmployerCredentialsRepository,
    metricsService: MetricsService,
    generator: ActivationCodeGenerator
) : EmployerService, BeingServiceBase<Employer, EmployerCreationForm, EmployerCredentials, EmployerActivation>(
    repository, passwordEncoder, smtpMailerService, activationRepository, credentialsRepository, metricsService,
    generator, EmployerActivation::createWithGeneratedCode
)
