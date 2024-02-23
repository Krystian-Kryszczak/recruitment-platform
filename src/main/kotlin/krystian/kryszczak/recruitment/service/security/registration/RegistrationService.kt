package krystian.kryszczak.recruitment.service.security.registration

import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.Item
import reactor.core.publisher.Mono

interface RegistrationService<T : Item, S : Formation<T>> {
    fun register(formation: S): Mono<Boolean>
}
