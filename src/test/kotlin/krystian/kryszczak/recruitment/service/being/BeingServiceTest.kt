package krystian.kryszczak.recruitment.service.being

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.service.DataAccessServiceTest

abstract class BeingServiceTest<T : Being, S : Formation<T>>(
    beingService: BeingService<T, S>,
    givenItems: Array<T>,
    updateFun: (T) -> T,
    copyWithId: (item: T, id: String) -> T,
    body: FreeSpec.() -> Unit = {}
) : DataAccessServiceTest<T>(beingService, givenItems, updateFun, copyWithId, body)
