package krystian.kryszczak.recruitment.service.security.registration

import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Item
import reactor.core.publisher.Mono

interface RegistrationService<T : Item, S : CreationForm<T, S>> {
    fun register(form: S): Mono<Boolean>
    fun completeAccountActivation(email: String, code: String): Mono<Boolean>
}
