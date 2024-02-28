package krystian.kryszczak.recruitment.service.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.repository.being.candidate.CandidateRepository
import krystian.kryszczak.recruitment.repository.security.code.activation.being.candidate.CandidateActivationRepository
import krystian.kryszczak.recruitment.repository.security.credentials.being.candidate.CandidateCredentialsRepository
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator

import krystian.kryszczak.recruitment.service.being.BeingServiceBase
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService

@Singleton
class CandidateServiceImpl(
    repository: CandidateRepository,
    passwordEncoder: PasswordEncoder,
    smtpMailerService: SmtpMailerService,
    activationRepository: CandidateActivationRepository,
    credentialsRepository: CandidateCredentialsRepository,
    metricsService: MetricsService,
    generator: ActivationCodeGenerator
) : CandidateService, BeingServiceBase<Candidate, CandidateFormation, CandidateCredentials, CandidateActivation>(
    repository, passwordEncoder, smtpMailerService, activationRepository, credentialsRepository, metricsService,
    generator, CandidateActivation::createWithGeneratedCode
)
