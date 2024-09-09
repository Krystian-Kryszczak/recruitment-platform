package krystian.kryszczak.recruitment.extension.authentication

import io.micronaut.security.authentication.Authentication

fun Authentication.getClientId() = attributes["ID"]?.toString()
