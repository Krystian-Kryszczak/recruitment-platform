package krystian.kryszczak.recruitment.service.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.being.candidate.CandidateRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.candidate.CandidateActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.candidate.CandidateCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.candidate.CandidateMapper
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.service.being.BeingServiceBase
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService

@Singleton
class DefaultCandidateService(
    repository: CandidateRepository,
    passwordEncoder: PasswordEncoder,
    smtpMailerService: SmtpMailerService,
    activationRepository: CandidateActivationRepository,
    credentialsRepository: CandidateCredentialsRepository,
    metricsService: MetricsService,
    generator: ActivationCodeGenerator,
    mapper: CandidateMapper
) : CandidateService, BeingServiceBase<Candidate, CandidateDto, CandidateCreationForm, CandidateUpdateForm, CandidateCredentials, CandidateActivation>(
    repository, passwordEncoder, smtpMailerService, activationRepository, credentialsRepository, metricsService,
    generator, mapper, CandidateActivation::createWithGeneratedCode
)
