package krystian.kryszczak.recruitment.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class WebController {
    @Get(produces = [MediaType.TEXT_PLAIN])
    fun index(): String = "Hello world!"
}
