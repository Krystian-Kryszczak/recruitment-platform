package krystian.kryszczak.recruitment.service.being.candidate

import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.service.being.BeingService

interface CandidateService : BeingService<Candidate, CandidateFormation>
