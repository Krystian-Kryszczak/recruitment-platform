package krystian.kryszczak.recruitment.security.encoder

import io.kotest.core.spec.style.FreeSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class PasswordEncoderTest(passwordEncoder: PasswordEncoder) : FreeSpec({
    "password encoder" {
        "raw password should matches with encoded password" - {
            // given
            val password = "Hello world!"

            // when
            val encoded = passwordEncoder.encode(password)

            // then
            passwordEncoder.matches(password, encoded)
        }

        "incorrect password should not matches with encoded password" - {
            // given
            val password = "Hello world!"
            val incorrect = "incorrect"

            // when
            val encoded = passwordEncoder.encode(password)

            // then
            passwordEncoder.matches(incorrect, encoded)
        }
    }
})
