package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.UpdateForm
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.mapper.ItemMapper
import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Dto
import reactor.core.publisher.Mono

abstract class AbstractExtendedDataAccessService<T : Item, S : Dto<T, S>, V : CreationForm<T, V>, U : UpdateForm<T, U>, ID>(
    repository: CrudRepositoryBase<T, ID>,
    private val mapper: ItemMapper<T, S, V, U, ID>
) : ExtendedDataAccessService<T, U, ID>, AbstractDataAccessService<T, ID>(repository) {
    override fun update(id: ID, form: U): Mono<T> =
        findById(id).flatMap { mapper.mapToOriginItem(it, form) }
}
