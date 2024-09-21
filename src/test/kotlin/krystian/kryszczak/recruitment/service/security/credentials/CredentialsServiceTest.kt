package krystian.kryszczak.recruitment.service.security.credentials

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import krystian.kryszczak.recruitment.service.DataAccessServiceTest

abstract class CredentialsServiceTest<E : Credentials>(
    credentialsService: CredentialsService<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.() -> Unit = {}
) : DataAccessServiceTest<E>(
    credentialsService,
    givenItems,
    updateMapper,
    copyWithId,
    body
)
