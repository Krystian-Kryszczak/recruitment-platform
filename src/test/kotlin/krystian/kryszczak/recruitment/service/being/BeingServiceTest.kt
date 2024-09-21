package krystian.kryszczak.recruitment.service.being

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.service.RestrictableDataAccessServiceTest

abstract class BeingServiceTest<T : Being<T>, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>>(
    beingService: BeingService<T, S, U>,
    givenItems: Array<T>,
    updateFun: (T) -> T,
    copyWithId: (item: T, id: String) -> T,
    body: FreeSpec.(givenItems: Array<T>) -> Unit = {}
) : RestrictableDataAccessServiceTest<T>(beingService, givenItems, updateFun, copyWithId, body)
