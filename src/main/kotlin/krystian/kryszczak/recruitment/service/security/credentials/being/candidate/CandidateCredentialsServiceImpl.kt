package krystian.kryszczak.recruitment.service.security.credentials.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.repository.security.credentials.being.candidate.CandidateCredentialsRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl

@Singleton
class CandidateCredentialsServiceImpl(repository: CandidateCredentialsRepository) :
    CandidateCredentialsService, DataAccessServiceImpl<CandidateCredentials>(repository)
