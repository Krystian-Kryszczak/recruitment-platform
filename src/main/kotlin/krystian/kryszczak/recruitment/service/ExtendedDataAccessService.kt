package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.UpdateForm
import reactor.core.publisher.Mono

interface ExtendedDataAccessService<E : Item, U : UpdateForm<E, U>, ID> : DataAccessService<E, ID> {
    fun update(id: ID, form: U): Mono<E>
}
