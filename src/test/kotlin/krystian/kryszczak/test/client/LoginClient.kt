package krystian.kryszczak.test.client

import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken

@Client("/")
interface LoginClient {
    @Post("/login")
    fun login(@Body credentials: UsernamePasswordCredentials): BearerAccessRefreshToken

    @Get(consumes = [TEXT_PLAIN])
    fun home(@Header(AUTHORIZATION) authorization: String): String
}
