package krystian.kryszczak.recruitment.repository.security.credentials

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

abstract class CredentialsRepositoryTest<E : Credentials>(
    repository: CredentialsRepository<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.() -> Unit = {}
) : CrudRepositoryBaseTest<E>(repository, givenItems, updateMapper, copyWithId, body)
