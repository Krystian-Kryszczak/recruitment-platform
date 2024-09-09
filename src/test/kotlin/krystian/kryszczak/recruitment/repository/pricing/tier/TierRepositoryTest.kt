package krystian.kryszczak.recruitment.repository.pricing.tier

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.pricing.tier.TierRepository
import krystian.kryszczak.recruitment.model.price.Price
import krystian.kryszczak.recruitment.model.pricing.tier.Tier
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

@MicronautTest(transactional = false)
class TierRepositoryTest(tierRepository: TierRepository) : CrudRepositoryBaseTest<Tier>(
tierRepository,
arrayOf(
    Tier(
        null,
        "Standard",
        Price(450.0, "PLN"),
        false,
        30,
        2,
        2,
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
        "Pro",
        Price(599.0, "PLN"),
        false,
        30,
        4,
        5,
        3,
        individualPromotionInSocialMedia = true,
        individualCustomerCare = true,
        jobAdPromotionInTechnologicalSummaries = false,
        graphicInTheJobAd = true,
        videoInTheJobAd = true,
        topPriorityInSimilarAds = true,
        topPriorityInSubscriptions = true,
        ableToBuy = true
    )
),
{ it.copy(displayName = "New " + it.displayName) },
{ item: Tier, id: String -> item.copy(id = id) }
)
