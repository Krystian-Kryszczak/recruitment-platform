package krystian.kryszczak.recruitment.repository.account.employer

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.account.employer.EmployerAccount
import krystian.kryszczak.recruitment.repository.account.AccountRepository

@MongoRepository
interface EmployerAccountRepository : AccountRepository<EmployerAccount>
