package krystian.kryszczak.recruitment.service.security.registration

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.Item

abstract class RegistrationServiceTest<T : Item, S : CreationForm<T, S>>(
    registrationService: RegistrationService<T, S>,
    body: FreeSpec.() -> Unit = {}
) : FreeSpec({
    "register service test" - {
        "test" {} // TODO
    }

    body(this)
})
