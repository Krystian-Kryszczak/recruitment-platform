package krystian.kryszczak.recruitment.service.security.credentials.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.candidate.CandidateCredentialsRepository
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.security.credentials.being.BeingCredentialsServiceBase

@Singleton
class DefaultCandidateCredentialsService(repository: CandidateCredentialsRepository) : CandidateCredentialsService,
    BeingCredentialsServiceBase<CandidateCredentials>(repository)
