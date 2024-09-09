package krystian.kryszczak.recruitment.mapper.being

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.mapper.ItemMapperTest
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials

abstract class BeingMapperTest<T : Being, S : BeingDto<T, S>, V : BeingCreationForm<T, V>, U : BeingUpdateForm<T, U>, C : BeingCredentials, A : BeingActivation<T, V, C>>(
    itemMapper: BeingMapper<T, S, V, U, C, A>,
    creationFormAndIdAndExcepted: Triple<V, String, T>,
    actualAndUpdateFormAndExcepted: Triple<T, U, T>,
    itemAndExceptedDto: Pair<T, S>,
    itemAndExceptedUpdateForm: Pair<T, U>,
    body: FreeSpec.() -> Unit = {}
) : ItemMapperTest<T, S, V, U, String>(
    itemMapper,
    creationFormAndIdAndExcepted,
    actualAndUpdateFormAndExcepted,
    itemAndExceptedDto,
    itemAndExceptedUpdateForm,
{
    "being mapper test" - {
        "map to credentials" {
            // TODO
        }
    }

    body(this)
})
