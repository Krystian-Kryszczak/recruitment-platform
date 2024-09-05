package krystian.kryszczak.recruitment.security.authentication.provider

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.security.authentication.AuthenticationService
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class LoginIncludesRefreshTokenTest(@Client("/") private val client: HttpClient) : FreeSpec({
    "upon successful authentication user gets access token and refresh token" {
        // given
        val credentials = UsernamePasswordCredentials("sherlock", "password")
        val request = HttpRequest.POST("/login", credentials)
        val response = client.toBlocking().retrieve(request, BearerAccessRefreshToken::class.java)

        // when
        response.username shouldBe "sherlock"
        val accessToken = response.accessToken.shouldNotBeNull()
        response.refreshToken.shouldNotBeNull()

        // then
        JWTParser.parse(accessToken)
            .shouldBeInstanceOf<SignedJWT>()
    }
}) {
    @MockBean(AuthenticationService::class)
    fun authenticationService(): AuthenticationService {
        val service = mockk<AuthenticationService>()

        every {
            service.authenticate(any(), UsernamePasswordCredentials("sherlock", "password"))
        } returns Mono.just(AuthenticationResponse.success("sherlock"))

        return service
    }
}
