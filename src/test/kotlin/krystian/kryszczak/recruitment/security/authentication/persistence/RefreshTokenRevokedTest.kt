package krystian.kryszczak.recruitment.security.authentication.persistence

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus.BAD_REQUEST
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.endpoints.TokenRefreshRequest
import io.micronaut.security.token.generator.RefreshTokenGenerator
import io.micronaut.security.token.render.BearerAccessRefreshToken
import krystian.kryszczak.recruitment.model.security.refresh.RefreshToken
import krystian.kryszczak.recruitment.repository.security.refresh.RefreshTokenRepository

class RefreshTokenRevokedTest : FreeSpec({
    val embeddedServer = ApplicationContext.run(EmbeddedServer::class.java, emptyMap())
    val client = embeddedServer.applicationContext.createBean(HttpClient::class.java, embeddedServer.url)
    val refreshTokenGenerator = embeddedServer.applicationContext.getBean(RefreshTokenGenerator::class.java)
    val refreshTokenRepository = embeddedServer.applicationContext.getBean(RefreshTokenRepository::class.java)

    "accessing Secured URL Without AuthenticatingReturnsUnauthorized" {
        val user = Authentication.build("sherlock")
        val refreshToken = refreshTokenGenerator.createKey(user)
        val refreshTokenOptional = refreshTokenGenerator.generate(user, refreshToken)
        refreshTokenOptional.isPresent shouldBe true

        val oldTokenCount = refreshTokenRepository.count().block().shouldNotBeNull()
        val signedRefreshToken = refreshTokenOptional.get()
        refreshTokenRepository.save(RefreshToken(null, user.name, refreshToken, true)).block()
        refreshTokenRepository.count().block().shouldNotBeNull() shouldBe oldTokenCount + 1

        val bodyArgument = Argument.of(BearerAccessRefreshToken::class.java)
        val errorArgument = Argument.of(Map::class.java)
        val exception = shouldThrow<HttpClientResponseException> {
            client.toBlocking().exchange(
                HttpRequest.POST("/oauth/access_token", TokenRefreshRequest(TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN, signedRefreshToken)),
                bodyArgument,
                errorArgument)
        }

        exception.status shouldBe BAD_REQUEST

        val optional = exception.response.getBody(Map::class.java)
        optional.isPresent shouldBe true

        val body = optional.get()
        body["error"] shouldBe "invalid_grant"
        body["error_description"] shouldBe "refresh token revoked"

        refreshTokenRepository.deleteAll()
    }
})
