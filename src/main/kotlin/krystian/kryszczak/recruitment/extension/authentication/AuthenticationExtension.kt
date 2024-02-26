package krystian.kryszczak.recruitment.extension.authentication

import io.micronaut.security.authentication.Authentication

fun Authentication.getClientUserRole() = attributes["ROLE"]?.toString()?.uppercase()
fun Authentication.getClientId() = attributes["ID"]?.toString()
