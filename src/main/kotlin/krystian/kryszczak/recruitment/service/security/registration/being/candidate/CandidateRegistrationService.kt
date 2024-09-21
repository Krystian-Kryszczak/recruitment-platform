package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationService

interface CandidateRegistrationService : BeingRegistrationService<Candidate, CandidateCreationForm, CandidateUpdateForm, CandidateDto, CandidateCredentials, CandidateActivation>
