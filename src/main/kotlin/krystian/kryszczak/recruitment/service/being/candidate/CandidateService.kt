package krystian.kryszczak.recruitment.service.being.candidate

import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.service.being.BeingService

interface CandidateService : BeingService<Candidate, CandidateCreationForm, CandidateUpdateForm>
