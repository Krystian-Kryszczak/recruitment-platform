package krystian.kryszczak.recruitment.service.pricing

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.database.mongodb.repository.pricing.tier.TierRepository
import krystian.kryszczak.recruitment.model.price.Price
import krystian.kryszczak.recruitment.model.pricing.tier.Tier
import krystian.kryszczak.recruitment.service.AbstractDataAccessService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class MongoPricingService(private val tierRepository: TierRepository) : PricingService, AbstractDataAccessService<Tier, String>(tierRepository) {
    override fun saveDefaultTiersIfNoneExists(): Mono<Boolean> {
        return tierRepository.count()
            .filter { it < 1 }
            .flatMapMany { tierRepository.saveAll(defaultTiers()) }
            .hasElements()
    }

    override fun findAll(): Flux<Tier> = tierRepository.findAll() // TODO caching

    private fun defaultTiers() = listOf(
        Tier(
            null,
            "Free",
            Price(0.0, "PLN"),
            false,
            30,
            0,
            0,
            1,
            individualPromotionInSocialMedia = false,
            individualCustomerCare = false,
            jobAdPromotionInTechnologicalSummaries = false,
            graphicInTheJobAd = false,
            videoInTheJobAd = false,
            topPriorityInSimilarAds = false,
            topPriorityInSubscriptions = false,
            ableToBuy = true
        ),
        Tier(
            null,
            "Basic",
            Price(199.0, "PLN"),
            false,
            30,
            1,
            0,
            2,
            individualPromotionInSocialMedia = false,
            individualCustomerCare = true,
            jobAdPromotionInTechnologicalSummaries = false,
            graphicInTheJobAd = true,
            videoInTheJobAd = false,
            topPriorityInSimilarAds = false,
            topPriorityInSubscriptions = false,
            ableToBuy = true
        ),
        Tier(
            null,
            "Standard",
            Price(450.0, "PLN"),
            false,
            30,
            2,
            2,
            4,
            individualPromotionInSocialMedia = false,
            individualCustomerCare = true,
            jobAdPromotionInTechnologicalSummaries = false,
            graphicInTheJobAd = true,
            videoInTheJobAd = false,
            topPriorityInSimilarAds = false,
            topPriorityInSubscriptions = false,
            ableToBuy = true
        ),
        Tier(
            null,
            "Pro",
            Price(599.0, "PLN"),
            true,
            30,
            2,
            4,
            5,
            individualPromotionInSocialMedia = true,
            individualCustomerCare = true,
            jobAdPromotionInTechnologicalSummaries = false,
            graphicInTheJobAd = true,
            videoInTheJobAd = true,
            topPriorityInSimilarAds = true,
            topPriorityInSubscriptions = true,
            ableToBuy = true
        ),
        Tier(
            null,
            "Enterprise",
            Price(999.0, "PLN"),
            true,
            30,
            4,
            5,
            8,
            individualPromotionInSocialMedia = true,
            individualCustomerCare = true,
            jobAdPromotionInTechnologicalSummaries = true,
            graphicInTheJobAd = true,
            videoInTheJobAd = true,
            topPriorityInSimilarAds = true,
            topPriorityInSubscriptions = true,
            ableToBuy = true
        )
    )
}
