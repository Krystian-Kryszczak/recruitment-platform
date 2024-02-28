package krystian.kryszczak.recruitment.service.being

import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Mono

interface BeingService<T : Being, S : Formation<T>> : DataAccessService<T, String> {
    fun register(formation: S, password: String): Mono<Boolean>

    fun completeActivation(email: String, code: String): Mono<Boolean>

    fun autoDeleteByUser(password: String, clientId: String): Mono<Boolean>
}
