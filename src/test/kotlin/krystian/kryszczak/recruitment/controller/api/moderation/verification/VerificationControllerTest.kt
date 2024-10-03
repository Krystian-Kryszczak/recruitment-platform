package krystian.kryszczak.recruitment.controller.api.moderation.verification

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.security.token.generator.TokenGenerator
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.management.verification.VerificationService
import krystian.kryszczak.test.util.generateToken
import reactor.core.publisher.Mono
import java.util.*

abstract class VerificationControllerTest(
    private val httpClient: HttpClient,
    protected val generator: TokenGenerator,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    "verification controller test" - {
        val client = httpClient.toBlocking()

        "verify" {
            // given
            val request = HttpRequest.POST("/verify/employer/${UUID.randomUUID()}", "")
                .bearerAuth(generateToken(listOf("ADMIN"), generator))

            // when
            val response = client.exchange(request, String::class.java)

            // then
            response.status shouldBe HttpStatus.ACCEPTED
        }

        "unverify" {
            // given
            val request = HttpRequest.POST("/unverify/employer/${UUID.randomUUID()}", "")
                .bearerAuth(generateToken(listOf("ADMIN"), generator))

            // when
            val response = client.exchange(request, String::class.java)

            // then
            response.status shouldBe HttpStatus.ACCEPTED
        }

        "is verified" {
            // given
            val request = HttpRequest.GET<String>("/is-verified/employer/${UUID.randomUUID()}")
                .bearerAuth(generateToken(listOf("ADMIN"), generator))

            // when
            val response = client.exchange(request, String::class.java)

            // then
            response.status shouldBe HttpStatus.OK
            response.body()
                .shouldNotBeBlank()
                .shouldBe("true")
        }
    }

    body(this)
}) {
    protected inline fun <T : Any, reified ID : Any, reified R : VerificationService<T, ID>> verificationService(result: T): R {
        return mockk<R> {
            every { verifyById(any()) } returns Mono.just(result)
            every { unverifyById(any()) } returns Mono.just(result)
            every { isVerifiedById(any()) } returns Mono.just(true)
        }
    }
}
