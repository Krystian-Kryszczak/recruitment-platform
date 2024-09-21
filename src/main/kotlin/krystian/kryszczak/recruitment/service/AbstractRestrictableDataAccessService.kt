package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.mapper.ItemMapper
import krystian.kryszczak.recruitment.model.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class AbstractRestrictableDataAccessService<T : RestrictableItem<T>, S : Dto<T, S>, V : CreationForm<T, V>, U : UpdateForm<T, U>, ID>(
    repository: CrudRepositoryBase<T, ID>,
    mapper: ItemMapper<T, S, V, U, ID>
) : AbstractExtendedDataAccessService<T, S, V, U, ID>(repository, mapper), RestrictableDataAccessService<T, U, ID> {
    override fun isBannedById(id: ID): Mono<Boolean> =
        repository.findById(id)
            .map(RestrictableItem<T>::isBanned)

    override fun banById(id: ID): Mono<Boolean> =
        repository.findById(id)
            .map(RestrictableItem<T>::copyBanned)
            .flatMap(repository::update)
            .hasElement()

    override fun unbanById(id: ID): Mono<Boolean> =
        repository.findById(id)
            .map(RestrictableItem<T>::copyUnbanned)
            .flatMap(repository::update)
            .hasElement()

    protected fun Mono<T>.filter() = filter(RestrictableItem<T>::filterTest)
    protected fun Flux<T>.filter() = filter(RestrictableItem<T>::filterTest)
}
