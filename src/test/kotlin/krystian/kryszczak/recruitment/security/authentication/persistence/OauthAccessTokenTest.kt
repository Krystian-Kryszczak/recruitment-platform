package krystian.kryszczak.recruitment.security.authentication.persistence

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.endpoints.TokenRefreshRequest
import io.micronaut.security.token.render.AccessRefreshToken
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.repository.security.refresh.RefreshTokenRepository

@MicronautTest(transactional = false)
class OauthAccessTokenTest(@Client("/") client: HttpClient, refreshTokenRepository: RefreshTokenRepository) : FreeSpec({
    "verify JWT access token refresh works" {
        val username = "sherlock"

        val credentials = UsernamePasswordCredentials(username, "password")
        val request = HttpRequest.POST("/login", credentials)

        val oldTokenCount = refreshTokenRepository.count().block().shouldNotBeNull()
        val rsp: BearerAccessRefreshToken = client.toBlocking().retrieve(request, BearerAccessRefreshToken::class.java)
        Thread.sleep(3000)
        refreshTokenRepository.count().block().shouldNotBeNull() shouldBe oldTokenCount + 1

        rsp.accessToken.shouldNotBeNull()
        rsp.refreshToken.shouldNotBeNull()

        Thread.sleep(1000) // sleep for one second to give time for the issued at `iat` Claim to change
        val refreshResponse = client.toBlocking().retrieve(HttpRequest.POST("/oauth/access_token",
            TokenRefreshRequest(TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN, rsp.refreshToken)
        ), AccessRefreshToken::class.java)

        refreshResponse.accessToken.shouldNotBeNull()
        refreshResponse.accessToken shouldNotBe rsp.accessToken

        refreshTokenRepository.deleteAll()
    }
})
