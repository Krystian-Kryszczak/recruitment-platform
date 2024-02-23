package krystian.kryszczak.recruitment.service.security.authentication

import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import reactor.core.publisher.Flux

interface AuthenticationService {
    fun authenticate(authenticationRequest: AuthenticationRequest<*, *>): Flux<AuthenticationResponse>
}
