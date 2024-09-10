package krystian.kryszczak.recruitment.mapper.exhibit

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.mapper.ItemMapperTest
import krystian.kryszczak.recruitment.model.exhibit.Exhibit
import krystian.kryszczak.recruitment.model.exhibit.ExhibitCreationForm
import krystian.kryszczak.recruitment.model.exhibit.ExhibitDto
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm

abstract class ExhibitMapperTest<T : Exhibit, S : ExhibitDto<T, S>, V : ExhibitCreationForm<T, V>, U : ExhibitUpdateForm<T, U>>(
    itemMapper: ExhibitMapper<T, S, V, U>,
    creationFormAndIdAndExcepted: Triple<V, String, T>,
    actualAndUpdateFormAndExcepted: Triple<T, U, T>,
    itemAndExceptedDto: Pair<T, S>,
    itemAndExceptedUpdateForm: Pair<T, U>,
    copyId: (item: T, id: String) -> T,
    body: FreeSpec.() -> Unit = {}
) : ItemMapperTest<T, S, V, U, String>(
    itemMapper,
    creationFormAndIdAndExcepted,
    actualAndUpdateFormAndExcepted,
    itemAndExceptedDto,
    itemAndExceptedUpdateForm,
    copyId,
    body
)
