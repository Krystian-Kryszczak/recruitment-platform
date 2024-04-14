package krystian.kryszczak.recruitment.service.security.authentication

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import reactor.core.publisher.Flux

interface AuthenticationService {
    fun authenticate(context: HttpRequest<*>, authenticationRequest: AuthenticationRequest<*, *>): Flux<AuthenticationResponse>
}
