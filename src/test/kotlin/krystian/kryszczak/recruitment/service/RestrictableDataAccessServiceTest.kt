package krystian.kryszczak.recruitment.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.model.RestrictableItem
import org.awaitility.Awaitility.await
import reactor.core.publisher.Mono

abstract class RestrictableDataAccessServiceTest<E : RestrictableItem<E>>(
    dataAccessService: RestrictableDataAccessService<E, *, String>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.(givenItems: Array<E>) -> Unit = {}
) : DataAccessServiceTest<E>(
dataAccessService,
givenItems,
updateMapper,
copyWithId, {
    "restrictable data access service test" - {
        fun doTest(action: RestrictableDataAccessService<E, *, String>.(id: String) -> Mono<Boolean>, excepted: Boolean = true) {
            // given
            val item = dataAccessService.save(givenItems.first())
                .block().shouldNotBeNull()
            val id = item.id.shouldNotBeNull()

            await().until { dataAccessService.findById(id).block() != null }

            // when
            val result = action(dataAccessService, id)
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(excepted)
        }

        "is banned by id" {
            doTest(RestrictableDataAccessService<E, *, String>::isBannedById, false)
        }

        "ban by id" {
            doTest(RestrictableDataAccessService<E, *, String>::banById)
        }

        "unban by id" {
            doTest(RestrictableDataAccessService<E, *, String>::unbanById)
        }
    }

    body(this, givenItems)
})
