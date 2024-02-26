package krystian.kryszczak.recruitment.security.authentication.persistence

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus.BAD_REQUEST
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.security.endpoints.TokenRefreshRequest
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class UnsignedRefreshTokenTest(@Client("/") client: HttpClient) : FreeSpec({
    "accessing secured URL without authenticating returns unauthorized" {
        val unsignedRefreshedToken = "foo"
        val bodyArgument = Argument.of(BearerAccessRefreshToken::class.java)
        val errorArgument = Argument.of(Map::class.java)

        val exception = shouldThrow<HttpClientResponseException> {
            client.toBlocking().exchange(
                HttpRequest.POST("/oauth/access_token", TokenRefreshRequest(TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN, unsignedRefreshedToken)),
                bodyArgument,
                errorArgument)
        }

        exception.status shouldBe BAD_REQUEST

        val optional = exception.response.getBody(Map::class.java)
        optional.isPresent shouldBe true

        val body = optional.get()
        body["error"] shouldBe "invalid_grant"
        body["error_description"] shouldBe "Refresh token is invalid"
    }
})
