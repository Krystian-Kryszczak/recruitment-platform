package krystian.kryszczak.recruitment.controller.api.admin

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
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
import krystian.kryszczak.recruitment.service.admin.AdminService
import krystian.kryszczak.test.util.generateToken
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class AdminControllerTest(@Client("/api/v1/admin/") client: HttpClient, generator: TokenGenerator) : FreeSpec({
    "admin controller" - {
        "ban employer" - {
            "should return OK status" {
                val result = client.toBlocking().exchange(
                    HttpRequest.POST("/employer/ban/${uniqueId()}", "")
                        .bearerAuth(generateToken(listOf("ADMIN"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
                result.body().shouldNotBeNull()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                arrayOf("EMPLOYER", "CANDIDATE", null).forEach {
                    shouldThrowWithMessage<HttpClientException>("Forbidden") {
                        client.toBlocking().exchange(
                            HttpRequest.POST("/employer/ban/${uniqueId()}", "")
                                .bearerAuth(generateToken(listOfNotNull(it), generator)),
                            String::class.java
                        )
                    }
                }
            }

            "should throw HTTP client exception with `Unauthorized` message" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.POST("/employer/ban/${uniqueId()}", ""),
                        String::class.java
                    )
                }
            }
        }

        "pardon employer" - {
            "should return OK status" {
                val result = client.toBlocking().exchange(
                    HttpRequest.POST("/employer/pardon/${uniqueId()}", "")
                        .bearerAuth(generateToken(listOf("ADMIN"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
                result.body().shouldNotBeNull()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                arrayOf("EMPLOYER", "CANDIDATE", null).forEach {
                    shouldThrowWithMessage<HttpClientException>("Forbidden") {
                        client.toBlocking().exchange(
                            HttpRequest.POST("/employer/pardon/${uniqueId()}", "")
                                .bearerAuth(generateToken(listOfNotNull(it), generator)),
                            String::class.java
                        )
                    }
                }
            }

            "should throw HTTP client exception with `Unauthorized` message" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.POST("/employer/pardon/${uniqueId()}", ""),
                        String::class.java
                    )
                }
            }
        }

        "ban candidate" - {
            "should return OK status" {
                val result = client.toBlocking().exchange(
                    HttpRequest.POST("/candidate/ban/${uniqueId()}", "")
                        .bearerAuth(generateToken(listOf("ADMIN"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
                result.body().shouldNotBeNull()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                arrayOf("EMPLOYER", "CANDIDATE", null).forEach {
                    shouldThrowWithMessage<HttpClientException>("Forbidden") {
                        client.toBlocking().exchange(
                            HttpRequest.POST("/candidate/ban/${uniqueId()}", "")
                                .bearerAuth(generateToken(listOfNotNull(it), generator)),
                            String::class.java
                        )
                    }
                }
            }

            "should throw HTTP client exception with `Unauthorized` message" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.POST("/candidate/ban/${uniqueId()}", ""),
                        String::class.java
                    )
                }
            }
        }

        "pardon candidate" - {
            "should return OK status" {
                val result = client.toBlocking().exchange(
                    HttpRequest.POST("/candidate/pardon/${uniqueId()}", "")
                        .bearerAuth(generateToken(listOf("ADMIN"), generator)),
                    String::class.java
                ).shouldNotBeNull()

                result.status shouldBe HttpStatus.ACCEPTED
                result.body().shouldNotBeNull()
            }

            "should throw HTTP client exception with `Forbidden` message" {
                arrayOf("EMPLOYER", "CANDIDATE", null).forEach {
                    shouldThrowWithMessage<HttpClientException>("Forbidden") {
                        client.toBlocking().exchange(
                            HttpRequest.POST("/candidate/pardon/${uniqueId()}", "")
                                .bearerAuth(generateToken(listOfNotNull(it), generator)),
                            String::class.java
                        )
                    }
                }
            }

            "should throw HTTP client exception with `Unauthorized` message" {
                shouldThrowWithMessage<HttpClientException>("Unauthorized") {
                    client.toBlocking().exchange(
                        HttpRequest.POST("/candidate/pardon/${uniqueId()}", ""),
                        String::class.java
                    )
                }
            }
        }
    }
}) {
    @MockBean(AdminService::class)
    fun mockService(): AdminService {
        val service = mockk<AdminService>()

        every { service.banEmployer(any()) } returns Mono.just(true)
        every { service.pardonEmployer(any()) } returns Mono.just(true)
        every { service.banCandidate(any()) } returns Mono.just(true)
        every { service.pardonCandidate(any()) } returns Mono.just(true)

        return service
    }
}
