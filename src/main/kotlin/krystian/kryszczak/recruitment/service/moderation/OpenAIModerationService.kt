package krystian.kryszczak.recruitment.service.moderation

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.client.openai.OpenAIHttpClient
import krystian.kryszczak.recruitment.model.moderation.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResults
import reactor.core.publisher.Mono

@Singleton
class OpenAIModerationService(private val httpClient: OpenAIHttpClient): ModerationService {
    override fun checkIfInputIsHarmful(input: String): Mono<Boolean> =
        httpClient.createModeration(ModerationRequest(input))
            .map(ModerationResponse::result)
            .map(ModerationResults::flagged)
}
