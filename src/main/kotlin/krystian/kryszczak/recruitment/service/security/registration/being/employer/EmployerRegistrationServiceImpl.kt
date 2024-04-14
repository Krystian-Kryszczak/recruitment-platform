package krystian.kryszczak.recruitment.service.security.registration.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import reactor.core.publisher.Mono

@Singleton
class EmployerRegistrationServiceImpl : EmployerRegistrationService {
    override fun register(formation: EmployerCreationForm): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}
