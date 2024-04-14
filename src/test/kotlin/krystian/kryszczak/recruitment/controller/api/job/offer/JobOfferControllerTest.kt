package krystian.kryszczak.recruitment.controller.api.job.offer

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.model.constant.EmploymentType
import krystian.kryszczak.recruitment.model.constant.Experience
import krystian.kryszczak.recruitment.model.constant.RecruitmentType
import krystian.kryszczak.recruitment.model.constant.TypeOfWork
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import krystian.kryszczak.recruitment.service.job.offer.JobOfferService
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Instant

@MicronautTest(transactional = false)
class JobOfferControllerTest(@Client("api/v1/job/offers/") client: HttpClient) : FreeSpec({
//    val mongoId = "507f1f77bcf86cd799439011"
//    val offerPath = "intent-firmware-engineer-freelance"

    "job offer controller" - {
        "search" - {
            "should return job offer list" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("?keyword=java"),
                    Argument.listOf(JobOffer::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }
        }
//        "find by id" - {}
//        "find by path" - {}
//        "get own" - {}
//        "add" - {}
//        "modify" - {}
//        "remove" - {}
    }
}) {
    @MockBean(JobOfferService::class)
    fun service(): JobOfferService { // TODO
        val service = mockk<JobOfferService>()

        val offer = JobOffer(
            uniqueId(),
            "java-senior-developer",
            uniqueId(),
            mapOf(),
            "Java",
            TypeOfWork.FULL_TIME,
            Experience.MID,
            EmploymentType.B2B,
            18000,
            22000,
            "PLN",
            mapOf("Java" to 5, "Micronaut" to 4, "Microservices" to 4),
            arrayOf("Warsaw"),
            RecruitmentType.ONLINE_INTERVIEW,
            true,
            Instant.now().plus(Duration.ofDays(30)),
            "e-corp-java-senior-developer"
        )

        every { service.search(any()) } returns Flux.just(offer)

        return service
    }
}
