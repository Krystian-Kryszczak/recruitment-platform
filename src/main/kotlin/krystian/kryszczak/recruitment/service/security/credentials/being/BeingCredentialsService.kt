package krystian.kryszczak.recruitment.service.security.credentials.being

import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.service.security.credentials.CredentialsService
import reactor.core.publisher.Mono

interface BeingCredentialsService<T : BeingCredentials> : CredentialsService<T> {
    fun updatePasswordById(id: String, password: String): Mono<Long>
}
