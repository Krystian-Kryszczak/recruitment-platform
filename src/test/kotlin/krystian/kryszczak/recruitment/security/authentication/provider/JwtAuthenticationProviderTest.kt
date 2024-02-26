package krystian.kryszczak.recruitment.security.authentication.provider

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.security.authentication.AuthenticationService
import krystian.kryszczak.test.client.LoginClient
import reactor.core.publisher.Flux

@MicronautTest(transactional = false)
class JwtAuthenticationProviderTest(loginClient: LoginClient) : FreeSpec({
    "verify JWT authentication works with declarative client" {
        val credentials = UsernamePasswordCredentials("sherlock", "password")
        val loginResponse: BearerAccessRefreshToken = loginClient.login(credentials)

        loginResponse.shouldNotBeNull()
        loginResponse.accessToken.shouldNotBeNull()
        JWTParser.parse(loginResponse.accessToken) should { it is SignedJWT }
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
