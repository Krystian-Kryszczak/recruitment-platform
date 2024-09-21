package krystian.kryszczak.recruitment.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Dto
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.UpdateForm

abstract class ItemMapperTest<T : Item, S : Dto<T, S>, V : CreationForm<T, V>, U : UpdateForm<T, U>, ID>(
    itemMapper: ItemMapper<T, S, V, U, ID>,
    creationFormAndIdAndExcepted: Triple<V, ID, T>,
    actualAndUpdateFormAndExcepted: Triple<T, U, T>,
    itemAndExceptedDto: Pair<T, S>,
    itemAndExceptedUpdateForm: Pair<T, U>,
    copyId: (item: T, id: ID) -> T,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    "item mapper test" - {
        "map to origin item (creationForm, doerId)" {
            // given
            val (creationForm, id, excepted) = creationFormAndIdAndExcepted

            // when
            val result = itemMapper.mapToOriginItem(creationForm, id)
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(copyId(excepted, result.id as ID))
        }

        "map to origin item (actual, updateForm)" {
            // given
            val (actual, updateForm, excepted) = actualAndUpdateFormAndExcepted

            // when
            val result = itemMapper.mapToOriginItem(actual, updateForm)
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(excepted)
        }

        "map to dto" {
            // given
            val (item, excepted) = itemAndExceptedDto

            // when
            val result = itemMapper.mapToDto(item)

            // then
            result.shouldNotBeNull()
                .shouldBe(excepted)
        }

        "map to update form" {
            // given
            val (item, excepted) = itemAndExceptedUpdateForm

            // when
            val result = itemMapper.mapToUpdateForm(item)

            // then
            result.shouldNotBeNull()
                .shouldBe(excepted)
        }
    }

    body(this)
})
