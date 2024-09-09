package krystian.kryszczak.recruitment.mapper.exhibit.job.offer

import io.kotest.mpp.uniqueId
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.mapper.exhibit.ExhibitMapperTest
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferDto
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.path.job.offer.JobOfferPathService
import krystian.kryszczak.recruitment.service.pricing.PricingService
import krystian.kryszczak.test.mock.tierMock
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant

@MicronautTest(transactional = false)
class JobOfferMapperTest(jobOfferMapper: JobOfferMapper) :
ExhibitMapperTest<JobOffer, JobOfferDto, JobOfferCreationForm, JobOfferUpdateForm>(
    jobOfferMapper,
    Triple(
        JobOfferCreationForm(
            "<basic-tier-id>",
            "Java Senior Developer",
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
            OperatingMode.REMOTE
        ),
        "<doer-id>",
        JobOffer(
            uniqueId(),
            "<basic-tier-id>",
            "Java Senior Developer",
            "<doer-id>",
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
    ),
    Triple(
        JobOffer(
            "<job-offer-id>",
            "<basic-tier-id>",
            "Java Senior Developer",
            "<doer-id>",
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
        ),
        JobOfferUpdateForm(
            minEarningsPerMonth = 20000,
            maxEarningsPerMonth = 24000,
            operatingMode = OperatingMode.HYBRID
        ),
        JobOffer(
            "<job-offer-id>",
            "<basic-tier-id>",
            "Java Senior Developer",
            "<doer-id>",
            mapOf(),
            "Java",
            TypeOfWork.FULL_TIME,
            Experience.SENIOR,
            EmploymentType.B2B,
            20000,
            24000,
            "PLN",
            mapOf("Java" to 5, "Micronaut" to 4, "Microservices" to 4),
            arrayOf("Warsaw"),
            RecruitmentType.ONLINE_INTERVIEW,
            OperatingMode.HYBRID,
            Instant.now().plus(Duration.ofDays(30)),
            setOf("e-corp-java-senior-developer"),
            false,
            Instant.now()
        )
    ),
    Pair(
        JobOffer(
            "<job-offer-id>",
            "<basic-tier-id>",
            "Kotlin Senior Developer",
            "<doer-id>",
            mapOf(),
            "Kotlin",
            TypeOfWork.FULL_TIME,
            Experience.SENIOR,
            EmploymentType.B2B,
            18000,
            22000,
            "PLN",
            mapOf("Kotlin" to 5, "Java" to 5, "Micronaut" to 4, "Microservices" to 4),
            arrayOf("Warsaw"),
            RecruitmentType.ONLINE_INTERVIEW,
            OperatingMode.REMOTE,
            Instant.now().plus(Duration.ofDays(30)),
            setOf("e-corp-kotlin-senior-developer"),
            false,
            Instant.now()
        ),
        JobOfferDto(
            "<job-offer-id>",
            "Kotlin Senior Developer",
            "<doer-id>",
            mapOf(),
            "Kotlin",
            TypeOfWork.FULL_TIME,
            Experience.SENIOR,
            EmploymentType.B2B,
            18000,
            22000,
            "PLN",
            mapOf("Kotlin" to 5, "Java" to 5, "Micronaut" to 4, "Microservices" to 4),
            arrayOf("Warsaw"),
            RecruitmentType.ONLINE_INTERVIEW,
            OperatingMode.REMOTE,
            Instant.now().plus(Duration.ofDays(30)),
            setOf("e-corp-kotlin-senior-developer"),
            Instant.now()
        )
    ),
    Pair(
        JobOffer(
            "<job-offer-id>",
            "<basic-tier-id>",
            "Kotlin Senior Developer",
            "<doer-id>",
            mapOf(),
            "Kotlin",
            TypeOfWork.FULL_TIME,
            Experience.SENIOR,
            EmploymentType.B2B,
            18000,
            22000,
            "PLN",
            mapOf("Kotlin" to 5, "Java" to 5, "Micronaut" to 4, "Microservices" to 4),
            arrayOf("Warsaw"),
            RecruitmentType.ONLINE_INTERVIEW,
            OperatingMode.REMOTE,
            Instant.now().plus(Duration.ofDays(30)),
            setOf("e-corp-kotlin-senior-developer"),
            false,
            Instant.now()
        ),
        JobOfferUpdateForm(
            "Kotlin Senior Developer",
            mapOf(),
            "Kotlin",
            TypeOfWork.FULL_TIME,
            Experience.SENIOR,
            EmploymentType.B2B,
            18000,
            22000,
            "PLN",
            mapOf("Kotlin" to 5, "Java" to 5, "Micronaut" to 4, "Microservices" to 4),
            arrayOf("Warsaw"),
            RecruitmentType.ONLINE_INTERVIEW,
            OperatingMode.REMOTE
        )
    )
) {
    @MockBean(JobOfferPathService::class)
    fun jobOfferPathService(): JobOfferPathService {
        val service = mockk<JobOfferPathService>()

        every { service.generatePath(any(), any(), any()) } returns Mono.just("e-corp-java-senior-developer")

        return service
    }

    @MockBean(PricingService::class)
    fun pricingService(): PricingService {
        val service = mockk<PricingService>()

        every { service.saveDefaultTiersIfNoneExists() } returns Mono.just(true)
        every { service.findById(any()) } returns Mono.just(tierMock)

        return service
    }
}
