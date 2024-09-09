package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.model.Item
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class AbstractDataAccessService<E : Item, ID>(internal open val repository: CrudRepositoryBase<E, ID>) : DataAccessService<E, ID> {
    override fun <S : E> save(entity: S): Mono<S> = repository.save(entity)

    override fun <S : E> saveAll(entities: Iterable<S>): Flux<S> = repository.saveAll(entities)

    override fun <S : E> update(entity: S): Mono<S> = repository.update(entity)

    override fun <S : E> updateAll(entities: Iterable<S>): Flux<S> = repository.updateAll(entities)

    override fun findById(id: ID): Mono<E> = repository.findById(id)

    override fun existsById(id: ID): Mono<Boolean> = repository.existsById(id)

    override fun count(): Mono<Long> = repository.count()

    override fun deleteById(id: ID): Mono<Long> = repository.deleteById(id)

    override fun delete(entity: E): Mono<Long> = repository.delete(entity)

    override fun deleteAll(entities: Iterable<E>): Mono<Long> = repository.deleteAll(entities)
}
