package krystian.kryszczak.recruitment.service.account

import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Mono

interface AccountService<T : Being, S : Formation<T>> : DataAccessService<T, String> {
    fun register(formation: S): Mono<String>
}
