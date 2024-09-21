package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.RestrictableItem
import krystian.kryszczak.recruitment.model.UpdateForm
import reactor.core.publisher.Mono

interface RestrictableDataAccessService<E : RestrictableItem<E>, U : UpdateForm<E, U>, ID> : ExtendedDataAccessService<E, U, ID> {
    fun isBannedById(id: ID): Mono<Boolean>
    fun banById(id: ID): Mono<Boolean>
    fun unbanById(id: ID): Mono<Boolean>
}
