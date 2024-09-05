package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.UpdateForm
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface DataAccessService<E, ID> {
    fun <S : E> save(entity: S): Mono< S>

    fun <S : E> saveAll(entities: Iterable<S>): Flux<S>

    fun <S : E> update(entity: S): Mono<S>

    fun <S : E> updateAll(entities: Iterable<S>): Flux<S>

    fun findById(id: ID): Mono<E>

    fun existsById(id: ID): Mono<Boolean>

    fun count(): Mono<Long>

    fun deleteById(id: ID): Mono<Long>

    fun delete(entity: E): Mono<Long>

    fun deleteAll(entities: Iterable<E>): Mono<Long>

    fun <S : UpdateForm<E, S>> update(id: ID, form: S, metadata: Map<String, Any> = mapOf()): Mono<E>
}
