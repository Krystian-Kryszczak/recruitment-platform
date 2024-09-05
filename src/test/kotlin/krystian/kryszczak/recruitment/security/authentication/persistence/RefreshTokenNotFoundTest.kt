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
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.endpoints.TokenRefreshRequest
import io.micronaut.security.token.generator.RefreshTokenGenerator
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class RefreshTokenNotFoundTest(@Client("/") client: HttpClient, refreshTokenGenerator: RefreshTokenGenerator) : FreeSpec({
    "accessing secured URL without authenticating returns unauthorized" {
        val user = Authentication.build("sherlock")
        val refreshToken = refreshTokenGenerator.createKey(user)
        val refreshTokenOptional = refreshTokenGenerator.generate(user, refreshToken)
        refreshTokenOptional.isPresent shouldBe true

        val signedRefreshToken = refreshTokenOptional.get()
        val bodyArgument = Argument.of(BearerAccessRefreshToken::class.java)
        val errorArgument = Argument.of(MutableMap::class.java)
        val req: HttpRequest<*> = HttpRequest.POST("/oauth/access_token", TokenRefreshRequest(TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN, signedRefreshToken))
        val e = shouldThrow<HttpClientResponseException> {
            client.toBlocking().exchange(req, bodyArgument, errorArgument)
        }
        e.status shouldBe BAD_REQUEST

        val optional = e.response.getBody(Map::class.java)
        optional.isPresent shouldBe true

        val body = optional.get()
        body["error"] shouldBe "invalid_grant"
        body["error_description"] shouldBe "refresh token not found"
    }
})
