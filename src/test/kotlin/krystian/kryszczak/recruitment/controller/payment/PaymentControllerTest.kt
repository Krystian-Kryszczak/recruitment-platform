package krystian.kryszczak.recruitment.controller.payment

import io.kotest.core.spec.style.FreeSpec
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class PaymentControllerTest(@Client("/payment/v1/") client: HttpClient) : FreeSpec({
    "payment controller service" - {
        // TODO
    }
})
