package krystian.kryszczak.recruitment.service.account.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.account.employer.EmployerAccount
import krystian.kryszczak.recruitment.repository.account.employer.EmployerAccountRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl

@Singleton
class EmployerAccountServiceImpl(employerAccountRepository: EmployerAccountRepository) :
    EmployerAccountService, DataAccessServiceImpl<EmployerAccount, String>(employerAccountRepository)
