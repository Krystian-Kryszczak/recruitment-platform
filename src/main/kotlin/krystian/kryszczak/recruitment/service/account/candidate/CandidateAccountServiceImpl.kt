package krystian.kryszczak.recruitment.service.account.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.account.candidate.CandidateAccount
import krystian.kryszczak.recruitment.repository.account.candidate.CandidateAccountRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl

@Singleton
class CandidateAccountServiceImpl(candidateAccountRepository: CandidateAccountRepository) :
    CandidateAccountService, DataAccessServiceImpl<CandidateAccount, String>(candidateAccountRepository)
