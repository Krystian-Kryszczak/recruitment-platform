package krystian.kryszczak.recruitment.controller.api.being

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.service.being.BeingService
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationService
import krystian.kryszczak.test.util.generateToken
import reactor.core.publisher.Mono

abstract class BeingControllerTest<T : Being<T>, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>, D : BeingDto<T, D>, C : BeingCredentials, A : BeingActivation<T, S, C>>(
    client: HttpClient,
    tokenGenerator: JwtTokenGenerator,
    updateForm: () -> U,
    createForm: () -> S,
    role: String,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    fun generateToken() = generateToken(listOf(role), tokenGenerator, uniqueId())

    "basic endpoints tests" - {
        "find by id" - {
            "should return OK status (path variable)" {
                client.toBlocking().exchange(
                    HttpRequest.GET<String>("/${uniqueId()}"),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }

            "should return OK status (client id)" {
                client.toBlocking().exchange(
                    HttpRequest.GET<String>("/")
                        .bearerAuth(generateToken()),
                    String::class.java
                ).shouldNotBeNull()
                .status shouldBe HttpStatus.OK
            }

            "should return NO CONTENT status" {
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
    protected inline fun <reified R : BeingService<T, S, U>, reified V : T, reified F : U> createBeingServiceMock(result: V): R {
        return mockk<R> {
            every { save(any<V>()) } returns Mono.just(result)

            every { findById(any()) } returns Mono.just(result)
            every { deleteById(any()) } returns Mono.just(Long.MAX_VALUE)
            every { autoDeleteByUser("sherlock", any()) } returns Mono.just(true)

            every { update(any(), any<F>()) } returns Mono.just(result)
        }
    }

    protected inline fun <reified R : BeingRegistrationService<T, S, U, D, C, A>, reified W : S> createBeingRegistrationServiceMock(): R {
        return mockk<R> {
            every { register(any<W>()) } returns Mono.just(true)
            every { completeAccountActivation(any(), any()) } returns Mono.just(true)
        }
    }
}
