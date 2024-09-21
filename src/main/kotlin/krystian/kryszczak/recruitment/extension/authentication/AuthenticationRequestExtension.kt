package krystian.kryszczak.recruitment.extension.authentication

import io.micronaut.security.authentication.AuthenticationRequest

operator fun <T, S> AuthenticationRequest<T, S>.component1(): String = identity.toString()
operator fun <T, S> AuthenticationRequest<T, S>.component2(): String = secret.toString()
