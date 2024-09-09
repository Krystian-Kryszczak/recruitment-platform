package krystian.kryszczak.recruitment.mapper

import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Dto
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.UpdateForm
import reactor.core.publisher.Mono

interface ItemMapper<T : Item, S : Dto<T, S>, V : CreationForm<T, V>, U : UpdateForm<T, U>, ID> {
    fun mapToOriginItem(form: V, doerId: ID): Mono<T>
    fun mapToOriginItem(actual: T, form: U): Mono<T>
    fun mapToDto(item: T): Mono<S>
    fun mapToUpdateForm(item: T): Mono<U>
}
