package krystian.kryszczak.recruitment.controller.api.being

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.service.being.BeingService
import reactor.core.publisher.Mono

abstract class BeingControllerTest<T : Being, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>>(
    client: HttpClient,
    tokenGenerator: JwtTokenGenerator,
    updateForm: () -> U,
    createForm: () -> S,
    role: String,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    fun generateToken() = tokenGenerator.generateToken(
        Authentication.build("john", listOf(role), mapOf("ID" to uniqueId())), 3600
    ).get()

    "basic endpoints tests" - {
        "find by id" - {
            "should return OK status" {
                client.toBlocking().exchange(
                    HttpRequest.GET<String>("/${uniqueId()}"),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }

            "should return not null value" {
                client.toBlocking().exchange(
                    HttpRequest.GET<String>("/")
                        .bearerAuth(generateToken()),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }

            "should return no content error" {
                client.toBlocking().exchange(
                    HttpRequest.GET<String>("/"),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.NO_CONTENT
            }
        }

        "remove" - {
            "should return OK status" {
                client.toBlocking().exchange(
                    HttpRequest.DELETE("/", mapOf("password" to "sherlock"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .bearerAuth(generateToken()),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }
        }

        "update" - {
            "should return OK status" {
                client.toBlocking().exchange(
                    HttpRequest.PUT("/", updateForm())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .bearerAuth(generateToken()),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }
        }

        "register" - {
            "should return OK status" {
                client.toBlocking().exchange(
                    HttpRequest.POST("/register", createForm())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }
        }

        "activate" - {
            "should return OK status" {
                client.toBlocking().exchange(
                    HttpRequest.POST(
                        "/activate", mapOf(
                        "email" to "eliot@fsociety.com",
                        "code" to uniqueId()
                    )).contentType(MediaType.APPLICATION_FORM_URLENCODED),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }
        }
    }

    body()
}) {
    protected inline fun <reified U : BeingService<T, S>, reified V : T, reified W : S> createServiceMock(result: V): U {
        val service = mockk<U>()

        every { service.save(any<V>()) } returns Mono.just(result)

        every { service.findById(any()) } returns Mono.just(result)
        every { service.deleteById(any()) } returns Mono.just(Long.MAX_VALUE)
        every { service.autoDeleteByUser("sherlock", any()) } returns Mono.just(true)

        every { service.update(any<V>()) } returns Mono.just(result)

        every { service.register(any<W>(), any()) } returns Mono.just(true)
        every { service.completeActivation(any(), any()) } returns Mono.just(true)

        return service
    }
}
