package krystian.kryszczak.recruitment.client.openai

import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import krystian.kryszczak.recruitment.model.moderation.openai.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.openai.response.ModerationResponse
import reactor.core.publisher.Mono

@Client(id = "open-ai")
@Header(name = AUTHORIZATION, value = "Bearer \${open-ai.token}")
interface OpenAIHttpClient {
    @Post(value = "/v1/moderations", produces = [MediaType.APPLICATION_JSON])
    fun createModeration(@Body body: ModerationRequest): Mono<ModerationResponse>
}
