package krystian.kryszczak.recruitment.service.account.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import krystian.kryszczak.recruitment.repository.being.employer.EmployerRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl
import reactor.core.publisher.Mono

@Singleton
class EmployerAccountServiceImpl(employerAccountRepository: EmployerRepository) :
    EmployerAccountService, DataAccessServiceImpl<Employer>(employerAccountRepository) {
    override fun register(formation: EmployerFormation): Mono<String> {
        TODO("Not yet implemented")
    }
}
