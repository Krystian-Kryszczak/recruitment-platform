package krystian.kryszczak.recruitment.service.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.service.RestrictableDataAccessService
import reactor.core.publisher.Mono

interface BeingService<T : Being<T>, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>> : RestrictableDataAccessService<T, U, String> {
    fun findByEmail(username: String): Mono<T>
    fun autoDeleteByUser(password: String, clientId: String): Mono<Boolean>
}
