package krystian.kryszczak.recruitment.service.security.code.activation

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.service.security.code.activation.account.AccountActivationService

abstract class AccountActivationServiceTest(accountActivationService: AccountActivationService, body: FreeSpec.() -> Unit = {}) : FreeSpec({
    "account activation service test" - {
        // TODO
    }

    body(this)
})
