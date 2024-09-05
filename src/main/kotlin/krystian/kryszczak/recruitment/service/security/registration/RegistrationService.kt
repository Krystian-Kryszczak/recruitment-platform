package krystian.kryszczak.recruitment.service.security.registration

import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Item
import reactor.core.publisher.Mono

interface RegistrationService<T : Item, S : CreationForm<T, S>> {
    fun register(formation: S): Mono<Boolean>
}
