package krystian.kryszczak.recruitment.service.verification

import io.kotest.core.spec.style.FreeSpec

abstract class VerificationServiceTest(verificationService: VerificationService, body: FreeSpec.() -> Unit = {}) : FreeSpec({
    "verification service test" - {
        // TODO
    }

    body(this)
})
