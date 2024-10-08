package krystian.kryszczak.recruitment.security.encoder;

import at.favre.lib.crypto.bcrypt.BCrypt
import jakarta.inject.Singleton

@Singleton
class BCryptPasswordEncoder: PasswordEncoder {
    override fun encode(rawPassword: String): String =
        BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray())

    override fun matches(rawPassword: String, encodedPassword: String) =
        BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword).verified
}
