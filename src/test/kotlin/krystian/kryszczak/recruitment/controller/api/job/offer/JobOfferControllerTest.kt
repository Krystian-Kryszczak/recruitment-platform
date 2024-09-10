package krystian.kryszczak.recruitment.controller.api.job.offer

import io.kotest.assertions.throwables.shouldThrowWithMessage
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
import io.micronaut.http.client.exceptions.HttpClientException
import io.micronaut.security.token.generator.TokenGenerator
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.job.offer.JobOfferService
import krystian.kryszczak.test.mock.jobOfferMock
import krystian.kryszczak.test.mock.tierMock
import krystian.kryszczak.test.util.generateToken
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class JobOfferControllerTest(@Client("/api/v1/job/offers/") client: HttpClient, generator: TokenGenerator) : FreeSpec({
    "job offer controller test" - {
        "search" - {
            "should return job offer list" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/search/?keyword=java"),
                    Argument.listOf(JobOffer::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }
        }

        "find by path or id" - {
            "should return job offer (by id)" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/${uniqueId()}"),
                    JobOffer::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeNull()
            }

            "should return job offer (by path)" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/some-job-offer-path"),
                    JobOffer::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeNull()
            }
        }

        "find by employer id" - {
            "should return job offer list" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/employer/${uniqueId()}"),
                    Argument.listOf(JobOffer::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }
        }

        "get own" - {
            "should return job offer list" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/own")
                        .bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                    Argument.listOf(JobOffer::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }
        }

        "add" - {
            "should return added job offer" {
                val result = client.toBlocking().exchange(
                    HttpRequest.POST(
                        "/",
                         JobOfferCreationForm(
                            tierMock.id!!,
                            "Junior Java Developer",
                            mapOf(),
                            "Java",
                            TypeOfWork.PART_TIME,
                            Experience.JUNIOR,
                            EmploymentType.PERMANENT,
                            5000,
                            10000,
                            "PLN",
                            mapOf("Java" to 5, "Micronaut" to 4, "Apache Cassandra" to 3),
                            arrayOf("Warsaw", "Poznań", "Gdańsk", "Toruń"),
                            RecruitmentType.ONLINE_INTERVIEW,
                            OperatingMode.ON_SITE
                        )
                    ).bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                    JobOffer::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.CREATED
                result.body().shouldNotBeNull()
            }

            "should throw HTTP client exception with `Unauthorized` message" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.POST(
                            "/",
                            JobOfferCreationForm(
                                tierMock.id!!,
                                "Junior Java Developer",
                                mapOf(),
                                "Java",
                                TypeOfWork.PART_TIME,
                                Experience.JUNIOR,
                                EmploymentType.PERMANENT,
                                5000,
                                10000,
                                "PLN",
                                mapOf("Java" to 5, "Micronaut" to 4, "Apache Cassandra" to 3),
                                arrayOf("Warsaw", "Poznań", "Gdańsk", "Toruń"),
                                RecruitmentType.ONLINE_INTERVIEW,
                                OperatingMode.ON_SITE
                            )
                        ),
                        JobOffer::class.java
                    )
                }
            }
        }

        "modify" - {
            "should return updated job offer" {
                val result = client.toBlocking().exchange(
                    HttpRequest.PUT(
                        "/${uniqueId()}",
                        JobOfferUpdateForm(
                            "Junior Java Developer",
                            mapOf(),
                            "Java",
                            TypeOfWork.PART_TIME,
                            Experience.JUNIOR,
                            EmploymentType.PERMANENT,
                            5000,
                            10000,
                            "PLN",
                            mapOf("Java" to 5, "Micronaut" to 4, "Apache Cassandra" to 3),
                            arrayOf("Warsaw", "Poznań", "Gdańsk", "Toruń"),
                            RecruitmentType.ONLINE_INTERVIEW,
                            OperatingMode.REMOTE
                        )
                    ).bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
            }
        }

        "remove" - {
            "should return OK status" {
                val result = client.toBlocking().exchange(
                    HttpRequest.DELETE("/${uniqueId()}", "")
                        .bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
            }
        }
    }
}) {
    @MockBean(JobOfferService::class)
    fun service(): JobOfferService {
        val service = mockk<JobOfferService>()

        every { service.search(any()) } returns Flux.just(jobOfferMock)
        every { service.findById(any()) } returns Mono.just(jobOfferMock)
        every { service.findByPath(any()) } returns Mono.just(jobOfferMock)
        every { service.findByPathOrId(any()) } returns Mono.just(jobOfferMock)
        every { service.findByEmployerId(any(), any(), any()) } returns Flux.just(jobOfferMock)
        every { service.findByEmployerAuth(any(), any()) } returns Flux.just(jobOfferMock)
        every { service.employerAdd(any(), any()) } returns Mono.just(jobOfferMock)
        every { service.employerRemove(any(), any()) } returns Mono.just(true)
        every { service.employerUpdate(any(), any(), any()) } returns Mono.just(true)

        return service
    }
}
