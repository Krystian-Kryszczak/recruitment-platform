package krystian.kryszczak.recruitment.controller.payment

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@ExecuteOn(TaskExecutors.IO)
@Controller("payment/v1/")
class PaymentController {
    // TODO
}
