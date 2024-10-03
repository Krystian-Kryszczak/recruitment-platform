package krystian.kryszczak.recruitment.service.security.registration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Item

abstract class RegistrationServiceTest<T : Item, S : CreationForm<T, S>>(
    registrationService: RegistrationService<T, S>,
    registerGiven: List<Pair<S, Boolean>>,
    completeActivationGiven: List<Triple<String, String, Boolean>>,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    "register service test" - {
        "register" {
            for ((given, excepted) in registerGiven) {
                // when
                val result = registrationService.register(given)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }

        "complete account activation" {
            for ((email, code, excepted) in completeActivationGiven) {
                // when
                val result = registrationService.completeAccountActivation(email, code)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }
    }

    body(this)
})
