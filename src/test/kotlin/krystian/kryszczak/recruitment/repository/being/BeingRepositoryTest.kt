package krystian.kryszczak.recruitment.repository.being

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.database.mongodb.repository.being.BeingRepository
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

abstract class BeingRepositoryTest<E : Being>(
    repository: BeingRepository<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.(Array<E>) -> Unit = {}
) : CrudRepositoryBaseTest<E>(repository, givenItems, updateMapper, copyWithId, body)
