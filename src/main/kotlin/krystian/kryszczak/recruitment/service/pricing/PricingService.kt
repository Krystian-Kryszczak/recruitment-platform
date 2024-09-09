package krystian.kryszczak.recruitment.service.pricing

import krystian.kryszczak.recruitment.model.pricing.tier.Tier
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PricingService : DataAccessService<Tier, String> {
    fun saveDefaultTiersIfNoneExists(): Mono<Boolean>
    fun findAll(): Flux<Tier>
}
