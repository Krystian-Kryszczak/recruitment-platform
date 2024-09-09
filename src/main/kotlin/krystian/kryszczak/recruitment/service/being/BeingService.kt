package krystian.kryszczak.recruitment.service.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.service.ExtendedDataAccessService
import reactor.core.publisher.Mono

interface BeingService<T : Being, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>> : ExtendedDataAccessService<T, U, String> {
    fun register(creationForm: S, password: String): Mono<Boolean>

    fun completeActivation(email: String, code: String): Mono<Boolean>

    fun autoDeleteByUser(password: String, clientId: String): Mono<Boolean>
}
