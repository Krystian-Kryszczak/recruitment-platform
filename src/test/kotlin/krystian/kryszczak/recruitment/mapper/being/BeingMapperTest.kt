package krystian.kryszczak.recruitment.mapper.being

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.mapper.ItemMapperTest
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials

abstract class BeingMapperTest<T : Being<T>, S : BeingDto<T, S>, V : BeingCreationForm<T, V>, U : BeingUpdateForm<T, U>, C : BeingCredentials, A : BeingActivation<T, V, C>>(
    itemMapper: BeingMapper<T, S, V, U, C, A>,
    creationFormAndIdAndExcepted: Triple<V, String, T>,
    actualAndUpdateFormAndExcepted: Triple<T, U, T>,
    itemAndExceptedDto: Pair<T, S>,
    itemAndExceptedUpdateForm: Pair<T, U>,
    activationAndExceptedBeing: Pair<A, T>,
    activationAndExceptedCredentials: Pair<A, C>,
    copyId: (item: T, id: String) -> T,
    body: FreeSpec.() -> Unit = {}
) : ItemMapperTest<T, S, V, U, String>(
    itemMapper,
    creationFormAndIdAndExcepted,
    actualAndUpdateFormAndExcepted,
    itemAndExceptedDto,
    itemAndExceptedUpdateForm,
    copyId,
{
    "being mapper test" - {
        "map to origin item" {
            // given
            val (activation, excepted) = activationAndExceptedBeing

            // when
            val result = itemMapper.mapToOriginItem(activation)
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(excepted)
        }

        "map to credentials" {
            // given
            val (activation, excepted) = activationAndExceptedCredentials

            // when
            val result = itemMapper.mapToCredentials(activation)

            // then
            result.shouldNotBeNull()
                .shouldBe(excepted)
        }
    }

    body(this)
})
