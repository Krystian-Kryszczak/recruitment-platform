package krystian.kryszczak.recruitment.security.authentication.provider

import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.micronaut.security.authentication.provider.HttpRequestReactiveAuthenticationProvider
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.service.security.authentication.AuthenticationService

@Singleton
class JwtAuthenticationProvider<T>(private val authenticationService: AuthenticationService) :
    HttpRequestReactiveAuthenticationProvider<T> {

    @SingleResult
    override fun authenticate(context: HttpRequest<T>, request: AuthenticationRequest<String, String>) =
        authenticationService.authenticate(context, request)
}
