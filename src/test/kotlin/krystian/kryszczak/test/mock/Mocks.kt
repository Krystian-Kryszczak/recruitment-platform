package krystian.kryszczak.test.mock

import io.kotest.mpp.uniqueId
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.identifier.business.BusinessIdentifiers
import krystian.kryszczak.recruitment.model.price.Price
import krystian.kryszczak.recruitment.model.pricing.tier.Tier
import java.time.Duration
import java.time.Instant

val candidateMock = Candidate(
    uniqueId(),
    "john@smith.eu",
    "John",
    "Smith",
    "123 456 789",
    "Hello there!",
    "linkedin.com",
    "github.com",
    arrayOf(),
    "Java/Kotlin Developer",
    5,
    arrayOf(),
    arrayOf(),
    mapOf(),
    arrayOf(),
    5,
    Sex.MALE,
    banned = false,
    agreeToEmailMarketing = true
)

val employerMock = Employer(
    uniqueId(),
    "E-Corp",
    "Hello world!",
    "Conglomerate",
    "FinTech",
    5,
    "https://mrrobot.fandom.com/wiki/E-Corp",
    "facebook.com",
    "instagram.com",
    "linkedin.com",
    "contact@e-corp.com",
    "123 456 789",
    arrayOf(),
    arrayOf(),
    "USA",
    setOf(BusinessIdentifiers("USA", "45678")),
    banned = false,
    agreeToEmailMarketing = true
)

val jobOfferMock = JobOffer(
    uniqueId(),
    uniqueId(),
    "Java Senior Developer",
    employerMock.id!!,
    mapOf(),
    "Java",
    TypeOfWork.FULL_TIME,
    Experience.SENIOR,
    EmploymentType.B2B,
    18000,
    22000,
    "PLN",
    mapOf("Java" to 5, "Micronaut" to 4, "Microservices" to 4),
    arrayOf("Warsaw"),
    RecruitmentType.ONLINE_INTERVIEW,
    OperatingMode.REMOTE,
    Instant.now().plus(Duration.ofDays(30)),
    setOf("e-corp-java-senior-developer"),
    false,
    Instant.now()
)

val jobApplicationMock = JobApplication(
    uniqueId(),
    jobOfferMock.id!!,
    candidateMock.id!!,
    "John",
    "Smith",
    "john.smith@gmail.com",
    "Hello there!",
    null,
    false,
    Instant.now()
)

val tierMock = Tier(
    "<tier-mock-id>",
    "Standard",
    Price(599.0, "PLN"),
    false,
    30,
    2,
    5,
    4,
    individualPromotionInSocialMedia = false,
    individualCustomerCare = false,
    jobAdPromotionInTechnologicalSummaries = false,
    graphicInTheJobAd = false,
    videoInTheJobAd = false,
    topPriorityInSimilarAds = true,
    topPriorityInSubscriptions = true,
    ableToBuy = true
)
