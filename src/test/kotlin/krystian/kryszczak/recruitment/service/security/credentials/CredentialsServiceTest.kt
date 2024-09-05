package krystian.kryszczak.recruitment.service.security.credentials

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.security.credentials.Credentials

abstract class CredentialsServiceTest<T : Credentials>(credentialsService: CredentialsService<T>, body: FreeSpec.() -> Unit = {}) : FreeSpec({
    "credentials service test" - {
        // TODO
    }

    body(this)
})
