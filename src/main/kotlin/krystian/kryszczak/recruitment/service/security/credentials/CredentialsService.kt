package krystian.kryszczak.recruitment.service.security.credentials

import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Mono

interface CredentialsService<T : Credentials> : DataAccessService<T, String> {
    fun findByUsername(username: String): Mono<T>
}
