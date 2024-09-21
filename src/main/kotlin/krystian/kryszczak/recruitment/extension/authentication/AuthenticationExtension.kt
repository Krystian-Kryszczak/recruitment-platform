package krystian.kryszczak.recruitment.extension.authentication

import io.micronaut.security.authentication.Authentication

fun Authentication.getClientId() = attributes["ID"]?.toString()
fun Authentication.isEmployer() = roles.contains("EMPLOYER")
fun Authentication.isCandidate() = roles.contains("CANDIDATE")
fun Authentication.isAdmin() = roles.contains("ADMIN")
