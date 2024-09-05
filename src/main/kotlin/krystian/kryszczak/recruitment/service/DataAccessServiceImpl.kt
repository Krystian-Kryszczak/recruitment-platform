package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.UpdateForm
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase
import reactor.core.publisher.Mono

abstract class DataAccessServiceImpl<T : Item>(repository: CrudRepositoryBase<T>) :
    DataAccessServiceBase<T, String>(repository) {

    override fun <S : UpdateForm<T, S>> update(id: String, form: S, metadata: Map<String, Any>): Mono<T> =
        findById(id).map { form.transform(it, metadata) }
}
