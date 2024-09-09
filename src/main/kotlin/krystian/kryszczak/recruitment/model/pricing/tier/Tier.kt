package krystian.kryszczak.recruitment.model.pricing.tier

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.price.Price

@Serdeable
@MappedEntity
data class Tier(
    @field:Id @field:GeneratedValue override val id: String?,
    val displayName: String,
    val price: Price,
    val highlighted: Boolean,
    val numberOfPublicationDays: Int,
    val adBumps: Int,
    val socialMediaPromotion: Int,
    val numberOfLocations: Int,
    val individualPromotionInSocialMedia: Boolean,
    val individualCustomerCare: Boolean,
    val jobAdPromotionInTechnologicalSummaries: Boolean,
    val graphicInTheJobAd: Boolean,
    val videoInTheJobAd: Boolean,
    val topPriorityInSimilarAds: Boolean,
    val topPriorityInSubscriptions: Boolean,
    val ableToBuy: Boolean
): Item(id)
