package krystian.kryszczak.test.extension.service

import io.micronaut.core.annotation.NonNull
import krystian.kryszczak.recruitment.service.DataAccessService
import krystian.kryszczak.recruitment.service.DataAccessServiceBase
import reactor.core.publisher.Mono

fun DataAccessService<*, *>.deleteAll(): @NonNull Mono<Long> {
    if (this is DataAccessServiceBase) {
        return this.repository.deleteAll()
    } else {
        throw RuntimeException()
    }
}
