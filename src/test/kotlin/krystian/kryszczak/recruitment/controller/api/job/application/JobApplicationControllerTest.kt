package krystian.kryszczak.recruitment.controller.api.job.application

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
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.service.exhibit.job.application.JobApplicationService
import krystian.kryszczak.test.mock.jobApplicationMock
import krystian.kryszczak.test.util.generateToken
import org.bson.types.ObjectId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class JobApplicationControllerTest(@Client("/api/v1/job/applications/") client: HttpClient, generator: TokenGenerator) : FreeSpec({
    "job application controller" - {
        "get by id" - {
            "should return job application" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/${uniqueId()}")
                        .bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                    JobApplication::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeNull()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                shouldThrowWithMessage<HttpClientException>("Forbidden") {
                    client.toBlocking().exchange(
                        HttpRequest.GET<String>("/${uniqueId()}")
                            .bearerAuth(generateToken(listOf("CANDIDATE"), generator)),
                        JobApplication::class.java
                    )
                }
            }
        }

        "get by offer id" - {
            "should return job applications list" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/offer/${ObjectId.get()}")
                        .bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                    Argument.listOf(JobApplication::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                shouldThrowWithMessage<HttpClientException>("Forbidden") {
                    client.toBlocking().exchange(
                        HttpRequest.GET<String>("/offer/${uniqueId()}")
                            .bearerAuth(generateToken(listOf("CANDIDATE"), generator)),
                        Argument.listOf(JobApplication::class.java)
                    )
                }
            }
        }

        "find own" - {
            "should return job applications list" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/own")
                        .bearerAuth(generateToken(listOf("CANDIDATE"), generator)),
                    Argument.listOf(JobApplication::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                shouldThrowWithMessage<HttpClientException>("Forbidden") {
                    client.toBlocking().exchange(
                        HttpRequest.GET<String>("/own")
                            .bearerAuth(generateToken(listOf("EMPLOYER"), generator)),
                        Argument.listOf(JobApplication::class.java)
                    )
                }
            }

            "should return job applications list - pagination" {
                val result = client.toBlocking().exchange(
                    HttpRequest.GET<String>("/own/5")
                        .bearerAuth(generateToken(listOf("CANDIDATE"), generator)),
                    Argument.listOf(JobApplication::class.java)
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.OK
                result.body().shouldNotBeEmpty()
            }

            "should throw HTTP client exception with `Unauthorized` message" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.GET<String>("/own"),
                        Argument.listOf(JobApplication::class.java)
                    )
                }
            }
        }

        "delete" - {
            "should return ACCEPTED status" {
                val result = client.toBlocking().exchange(
                    HttpRequest.DELETE<String>("/${uniqueId()}")
                        .bearerAuth(generateToken(listOf("CANDIDATE"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
            }

            "should throw FORBIDDEN exception status" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.DELETE<String>("/${uniqueId()}"),
                        JobApplication::class.java
                    )
                }
            }
        }
    }
}) {
    @MockBean(JobApplicationService::class)
    fun service(): JobApplicationService {
        val service = mockk<JobApplicationService>()

        every { service.findByIdForEmployerClient(any(), any()) } returns Mono.just(jobApplicationMock)
        every { service.findByOfferIdForEmployerClient(any(), any(), any()) } returns Flux.just(jobApplicationMock)
        every { service.findPublishedByCandidateClient(any(), any()) } returns Flux.just(jobApplicationMock)
        every { service.deleteOwnByIdForCandidateClient(any(), any()) } returns Mono.just(true)

        return service
    }
}
