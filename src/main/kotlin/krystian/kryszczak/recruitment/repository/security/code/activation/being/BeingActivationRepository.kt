package krystian.kryszczak.recruitment.repository.security.code.activation.being

import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.ActivationRepository
import reactor.core.publisher.Flux

interface BeingActivationRepository<T : BeingActivation<*, *, *>> : ActivationRepository<T> {
    fun findByIdentityAndCode(identity: String, code: String): Flux<T>
}
