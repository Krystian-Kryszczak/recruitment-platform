package krystian.kryszczak.recruitment.repository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.model.Item

abstract class CrudRepositoryBaseTest<E : Item>(
    repository: CrudRepositoryBase<E, String>,
    givenItems: Array<E>,
    updateMapper: (E) -> E,
    copyWithId: (item: E, id: String) -> E,
    body: FreeSpec.(Array<E>) -> Unit = {}
) : FreeSpec({
    // clean up
    afterAny {
        repository.deleteAll().block()
    }

    "data access service base tests" - {
        "save and findById" {
            // given
            val item = givenItems.first()

            // when
            val id = repository.save(item).block()?.id.shouldNotBeNull()

            // then
            repository.findById(id).block()
                .shouldNotBeNull()
                .shouldBe(copyWithId(item, id))
        }

        "saveAll" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = repository.saveAll(items).collectList().block()
                .shouldNotBeNull()

            // then
            for (item in saved) {
                val id = item.id.shouldNotBeNull();
                repository.findById(id).block()
                    .shouldNotBeNull()
                    .shouldBe(copyWithId(item, id))
            }
        }

        "update" {
            // given
            val item = givenItems.last()

            // when
            val id = repository.save(item).block()?.id.shouldNotBeNull()
            val updateItem = updateMapper(copyWithId(item, id))
            repository.update(updateItem).block()

            // then
            repository.findById(id).block()
                .shouldNotBeNull()
                .shouldBe(updateItem)
        }

        "updateAll" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = repository.saveAll(items).collectList().block()
                .shouldNotBeNull()
            for (item in saved) {
                val updateItem = updateMapper(item)
                repository.update(updateItem).block()

                // then
                repository.findById(item.id.shouldNotBeNull()).block()
                    .shouldNotBeNull()
                    .shouldBe(updateItem)
            }
        }

        "existsById" {
            // given
            val item = givenItems.first()

            // when
            val id = repository.save(item).block()?.id.shouldNotBeNull()

            // then
            repository.existsById(id).block()
                .shouldNotBeNull()
                .shouldBe(true)
        }

        "findAll" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = repository.saveAll(items).collectList().block()
                .shouldNotBeNull()

            // then
            repository.findAll().collectList().block()
                .shouldNotBeNull()
                .shouldContainAll(saved)
        }

        "count" {
            // given
            val items = givenItems

            // when
            repository.saveAll(items.asIterable()).blockLast()

            // then
            repository.count().block() shouldBe items.size
        }

        "deleteById" {
            // given
            val item = givenItems.first()

            // when
            val id = repository.save(item).block()?.id.shouldNotBeNull()
            repository.deleteById(id).block()

            // then
            repository.existsById(id).block()
                .shouldBe(false)
        }

        "delete" {
            // given
            val item = givenItems.first()

            // when
            val id = repository.save(item).block()?.id.shouldNotBeNull()
            repository.delete(copyWithId(item, id)).block()

            // then
            repository.existsById(id).block()
                .shouldBe(false)
        }

        "deleteAll items" {
            // given
            val items = givenItems.asIterable()

            // when
            val saved = repository.saveAll(items).collectList().block()
                .shouldNotBeNull()
            repository.deleteAll(saved).block()

            // then
            for (item in saved) {
                repository.existsById(item.id.shouldNotBeNull()).block()
                    .shouldBe(false)
            }
        }

        "deleteAll" {
            // given
            val items = givenItems.asIterable()

            // when
            repository.saveAll(items).collectList().block()
            repository.deleteAll().block()

            // then
            repository.count().block() shouldBe 0L
        }
    }

    body(this, givenItems)
})
