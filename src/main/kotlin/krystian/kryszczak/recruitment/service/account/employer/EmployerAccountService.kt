package krystian.kryszczak.recruitment.service.account.employer

import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import krystian.kryszczak.recruitment.service.account.AccountService

interface EmployerAccountService : AccountService<Employer, EmployerFormation>
