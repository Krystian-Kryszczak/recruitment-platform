package krystian.kryszczak.recruitment.repository.security.code.activation

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.ActivationRepository
import krystian.kryszczak.recruitment.model.security.code.activation.Activation
import krystian.kryszczak.recruitment.repository.security.code.CodeRepositoryTest

abstract class ActivationRepositoryTest<E : Activation>(
    repository: ActivationRepository<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.(Array<E>) -> Unit = {}
) : CodeRepositoryTest<E>(repository, givenItems, updateMapper, copyWithId, body)
