package krystian.kryszczak.recruitment.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.test.extension.service.deleteAll

abstract class DataAccessServiceTest<E: Item>(
    dataAccessService: DataAccessService<E, String>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    // clean up
    afterAny {
        dataAccessService.deleteAll().block()
    }

    "data access service base tests (${dataAccessService::class.simpleName})" - {
        "save and findById" {
            // given
            val item = givenItems.first()

            // when
            val id = dataAccessService.save(item).block()?.id.shouldNotBeNull()

            // then
            dataAccessService.findById(id).block()
                .shouldNotBeNull()
                .shouldBe(copyWithId(item, id))
        }

        "saveAll" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = dataAccessService.saveAll(items).collectList().block()
                .shouldNotBeNull()

            // then
            for (item in saved) {
                val id = item.id.shouldNotBeNull();
                dataAccessService.findById(id).block()
                    .shouldNotBeNull()
                    .shouldBe(copyWithId(item, id))
            }
        }

        "update" {
            // given
            val item = givenItems.last()

            // when
            val id = dataAccessService.save(item).block()?.id.shouldNotBeNull()
            val updateItem = updateMapper(copyWithId(item, id))
            dataAccessService.update(updateItem).block()

            // then
            dataAccessService.findById(id).block()
                .shouldNotBeNull()
                .shouldBe(updateItem)
        }

        "updateAll" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = dataAccessService.saveAll(items).collectList().block()
                .shouldNotBeNull()
            for (item in saved) {
                val updateItem = updateMapper(item)
                dataAccessService.update(updateItem).block()

                // then
                dataAccessService.findById(item.id.shouldNotBeNull()).block()
                    .shouldNotBeNull()
                    .shouldBe(updateItem)
            }
        }

        "existsById" {
            // given
            val item = givenItems.first()

            // when
            val id = dataAccessService.save(item).block()?.id.shouldNotBeNull()

            // then
            dataAccessService.existsById(id).block()
                .shouldNotBeNull()
                .shouldBe(true)
        }

        "count" {
            // given
            val items = givenItems

            // when
            dataAccessService.saveAll(items.asIterable()).blockLast()

            // then
            dataAccessService.count().block() shouldBe items.size
        }

        "deleteById" {
            // given
            val item = givenItems.first()

            // when
            val id = dataAccessService.save(item).block()?.id.shouldNotBeNull()
            dataAccessService.deleteById(id).block()

            // then
            dataAccessService.existsById(id).block()
                .shouldBe(false)
        }

        "delete" {
            // given
            val item = givenItems.first()

            // when
            val id = dataAccessService.save(item).block()?.id.shouldNotBeNull()
            dataAccessService.delete(copyWithId(item, id)).block()

            // then
            dataAccessService.existsById(id).block()
                .shouldBe(false)
        }

        "deleteAll" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = dataAccessService.saveAll(items).collectList().block()
                .shouldNotBeNull()
            dataAccessService.deleteAll(saved).block()

            // then
            for (item in saved) {
                dataAccessService.existsById(item.id.shouldNotBeNull()).block()
                    .shouldBe(false)
            }
        }
    }

    body.invoke(this)
})
