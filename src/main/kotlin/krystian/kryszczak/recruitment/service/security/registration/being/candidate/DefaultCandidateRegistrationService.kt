package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.candidate.CandidateActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.candidate.CandidateCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.candidate.CandidateMapper
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationServiceBase

@Singleton
class DefaultCandidateRegistrationService(
    activationRepository: CandidateActivationRepository,
    credentialsRepository: CandidateCredentialsRepository,
    candidateService: CandidateService,
    candidateMapper: CandidateMapper,
    smtpMailerService: SmtpMailerService,
) : BeingRegistrationServiceBase<Candidate, CandidateCreationForm, CandidateUpdateForm, CandidateDto, CandidateCredentials, CandidateActivation>(
    activationRepository, credentialsRepository, candidateService, candidateMapper, smtpMailerService
), CandidateRegistrationService
