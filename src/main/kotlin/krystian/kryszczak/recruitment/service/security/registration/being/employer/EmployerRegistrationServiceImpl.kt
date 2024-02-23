package krystian.kryszczak.recruitment.service.security.registration.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import reactor.core.publisher.Mono

@Singleton
class EmployerRegistrationServiceImpl : EmployerRegistrationService {
    override fun register(formation: EmployerFormation): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}
