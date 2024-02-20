package krystian.kryszczak.test.extension.service

import io.micronaut.core.annotation.NonNull
import krystian.kryszczak.recruitment.service.DataAccessService
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl
import reactor.core.publisher.Mono

fun DataAccessService<*, *>.deleteAll(): @NonNull Mono<Long> {
    if (this is DataAccessServiceImpl) {
        return this.repository.deleteAll()
    } else {
        throw RuntimeException()
    }
}
