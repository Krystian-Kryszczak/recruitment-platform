package krystian.kryszczak.recruitment.extension.authentication

import io.micronaut.security.authentication.Authentication
import krystian.kryszczak.recruitment.constatnt.role.UserRole

fun Authentication.getClientUserRole() = attributes["ROLE"]?.toString()?.let { UserRole.parse(it) }
fun Authentication.getClientId() = attributes["ID"]?.toString()
fun Authentication.isClientCandidate() = getClientUserRole() == UserRole.CANDIDATE
fun Authentication.isClientEmployer() = getClientUserRole() == UserRole.EMPLOYER
