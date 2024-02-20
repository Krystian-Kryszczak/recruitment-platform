package krystian.kryszczak.recruitment.controller.api

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("api/v1/")
@ExecuteOn(TaskExecutors.BLOCKING)
class SignInController
