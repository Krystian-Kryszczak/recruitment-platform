package krystian.kryszczak.recruitment.service.exhibit

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.exhibit.Exhibit
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm
import krystian.kryszczak.recruitment.service.RestrictableDataAccessServiceTest

abstract class ExhibitServiceTest<T : Exhibit<T>, U : ExhibitUpdateForm<T, U>>(
    exhibitService: ExhibitService<T, U, String>,
    givenItems: Array<T>,
    updateFun: (T) -> T,
    copyWithId: (item: T, id: String) -> T,
    body: FreeSpec.(givenItems: Array<T>) -> Unit = {}
) : RestrictableDataAccessServiceTest<T>(exhibitService, givenItems, updateFun, copyWithId, body)
