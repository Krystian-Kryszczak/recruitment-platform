package krystian.kryszczak.recruitment.service.payment

import io.kotest.core.spec.style.FreeSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class PaymentServiceTest(paymentService: PaymentService) : FreeSpec({
    "payment service test" - {
        // TODO
    }
})
