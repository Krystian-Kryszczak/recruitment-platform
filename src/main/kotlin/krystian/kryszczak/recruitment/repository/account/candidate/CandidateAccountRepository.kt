package krystian.kryszczak.recruitment.repository.account.candidate

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.account.candidate.CandidateAccount
import krystian.kryszczak.recruitment.repository.account.AccountRepository

@MongoRepository
interface CandidateAccountRepository : AccountRepository<CandidateAccount>
