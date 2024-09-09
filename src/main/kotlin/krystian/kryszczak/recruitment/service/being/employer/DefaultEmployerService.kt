package krystian.kryszczak.recruitment.service.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.being.employer.EmployerRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.employer.EmployerActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.employer.EmployerMapper
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.service.being.BeingServiceBase
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService
import reactor.core.publisher.Mono

@Singleton
class DefaultEmployerService(
    override val repository: EmployerRepository,
    passwordEncoder: PasswordEncoder,
    smtpMailerService: SmtpMailerService,
    activationRepository: EmployerActivationRepository,
    credentialsRepository: EmployerCredentialsRepository,
    metricsService: MetricsService,
    generator: ActivationCodeGenerator,
    mapper: EmployerMapper
) : EmployerService, BeingServiceBase<Employer, EmployerDto, EmployerCreationForm, EmployerUpdateForm, EmployerCredentials, EmployerActivation>(
    repository, passwordEncoder, smtpMailerService, activationRepository, credentialsRepository, metricsService,
    generator, mapper, EmployerActivation::createWithGeneratedCode
) {
    override fun getEmployerName(id: String): Mono<String> = repository.findNameById(id)
}
