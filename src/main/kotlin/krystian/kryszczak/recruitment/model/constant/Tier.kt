package krystian.kryszczak.recruitment.model.constant

import krystian.kryszczak.recruitment.model.price.Price

enum class Tier(
    displayName: String,
    price: Price
) {
    Free("", Price()),
    Basic("", Price()),
    Standard("", Price()),
    Pro("", Price()),
    Enterprise("", Price())
}

//                name: 'Pro',
//                    price: '599 PLN',
//                    highlighted: true,
//                    numberOfPublicationDays: 30,
//                    adBumps: 2,
//                    socialMediaPromotion: 5,
//                    numberOfLocations: 4,
//                    individualPromotionInSocialMedia: false,
//                    individualCustomerCare: false,
//                    jobAdOptimizationDuringPublication: false,
//                    jobAdPromotionInTechnologicalSummaries: false,
//                    graphicInTheJobAd: false,
//                    videoInTheJobAd: false,
//                    topPriorityInSimilarAds: true,
//                    topPriorityInSubscriptions: true,
//                    highlightedOffer: false,
//                    performanceCampaign: false,
//                    quickWinRecruitment: true