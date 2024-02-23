package krystian.kryszczak.recruitment.service.account.candidate

import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.service.account.AccountService

interface CandidateAccountService : AccountService<Candidate, CandidateFormation>
