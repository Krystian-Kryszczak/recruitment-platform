package krystian.kryszczak.recruitment.repository.security.code

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.security.code.Code
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

abstract class CodeRepositoryTest<E : Code>(
    repository: CodeRepository<E>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.() -> Unit = {}
) : CrudRepositoryBaseTest<E>(repository, givenItems, updateMapper, copyWithId, body)
