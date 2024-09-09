package krystian.kryszczak.recruitment.mapper.being.candidate

import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials

interface CandidateMapper : BeingMapper<Candidate, CandidateDto, CandidateCreationForm, CandidateUpdateForm, CandidateCredentials, CandidateActivation>
