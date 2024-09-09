package krystian.kryszczak.recruitment.extension.authentication

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.micronaut.security.authentication.Authentication

class AuthenticationExtensionTest : FreeSpec({
    "authentication extension test" - {
        "get client id" {
            // given
            val authentication = Authentication.build("john", listOf("EMPLOYER"), mapOf("ID" to "<user-id>"))

            // when
            val result = authentication.getClientId()

            // then
            result.shouldNotBeNull()
                .shouldNotBeBlank()
                .shouldBe("<user-id>")
        }
    }
})
