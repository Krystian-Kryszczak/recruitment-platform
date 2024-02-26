package krystian.kryszczak.recruitment.security.authentication.provider

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
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
import reactor.core.publisher.Flux

@MicronautTest(transactional = false)
class LoginIncludesRefreshTokenTest(@Client("/") private val client: HttpClient) : FreeSpec({
    "upon successful authentication user gets access token and refresh token" {
        val credentials = UsernamePasswordCredentials("sherlock", "password")
        val request = HttpRequest.POST("/login", credentials)
        val response = client.toBlocking().retrieve(request, BearerAccessRefreshToken::class.java)

        response.username shouldBe "sherlock"
        response.accessToken.shouldNotBeNull()
        response.refreshToken.shouldNotBeNull()

        JWTParser.parse(response.accessToken) should { it is SignedJWT }
    }
}) {
    @MockBean(AuthenticationService::class)
    fun authenticationService(): AuthenticationService {
        val service = mockk<AuthenticationService>()

        every {
            service.authenticate(any(), UsernamePasswordCredentials("sherlock", "password"))
        } returns Flux.just(AuthenticationResponse.success("sherlock"))

        return service
    }
}

