package krystian.kryszczak.recruitment.service.security.authentication

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import org.reactivestreams.Publisher

interface AuthenticationService {
    fun authenticate(context: HttpRequest<*>?, request: AuthenticationRequest<*, *>): Publisher<AuthenticationResponse>
}
