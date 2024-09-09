package krystian.kryszczak.recruitment.repository.security.code.activation.being

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.BeingActivationRepository
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.ActivationRepositoryTest

abstract class BeingActivationRepositoryTest<E : BeingActivation<*, *, *>>(
    repository: BeingActivationRepository<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.(Array<E>) -> Unit = {}
) : ActivationRepositoryTest<E>(repository, givenItems, updateMapper, copyWithId, body)
