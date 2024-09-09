package krystian.kryszczak.recruitment.service.pricing

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.database.mongodb.repository.pricing.tier.TierRepository
import krystian.kryszczak.recruitment.model.pricing.tier.Tier
import krystian.kryszczak.test.mock.tierMock
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class PricingServiceTest(pricingService: PricingService) : FreeSpec({
    "pricing service test" - {
        "save default tiers if none exists" {
            // when
            val result = pricingService.saveDefaultTiersIfNoneExists()
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBeTrue()
        }
    }
}) {
    @MockBean(TierRepository::class)
    fun tierRepository(): TierRepository {
        val repository = mockk<TierRepository>()

        every { repository.count() } returns Mono.just(0)
        every { repository.saveAll<Tier>(any()) } returns Flux.just(tierMock)

        return repository
    }
}
