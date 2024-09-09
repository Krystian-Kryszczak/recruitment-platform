package krystian.kryszczak.recruitment.repository.security.credentials.being

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.repository.security.credentials.CredentialsRepositoryTest

abstract class BeingCredentialsRepositoryTest<E : BeingCredentials>(
    repository: BeingCredentialsRepository<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.(Array<E>) -> Unit = {}
) : CredentialsRepositoryTest<E>(repository, givenItems, updateMapper, copyWithId, body)
