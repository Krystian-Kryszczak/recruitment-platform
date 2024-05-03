package krystian.kryszczak.test.util

import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.generator.TokenGenerator

fun generateToken(roles: List<String>, generator: TokenGenerator) = generator
    .generateToken(Authentication.build("john", roles), 3600).get()
