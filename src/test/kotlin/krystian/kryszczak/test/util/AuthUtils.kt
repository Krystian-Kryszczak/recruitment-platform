package krystian.kryszczak.test.util

import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.generator.TokenGenerator
import java.util.UUID

fun generateToken(roles: List<String>, generator: TokenGenerator, id: String = UUID.randomUUID().toString()) = generator
    .generateToken(Authentication.build("john", roles, mapOf("ID" to id)), 3600).get()
