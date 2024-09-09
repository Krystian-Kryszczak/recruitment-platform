package krystian.kryszczak.recruitment.service.moderation

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.client.openai.OpenAIHttpClient
import krystian.kryszczak.recruitment.model.moderation.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResults
import reactor.core.publisher.Mono

@Singleton
class OpenAIModerationService(private val httpClient: OpenAIHttpClient): ModerationService {
    // TODO saving moderation results in other location/way (in service/to different collection)
    override fun checkIfInputIsHarmful(input: String, id: String): Mono<Boolean> =
        httpClient.createModeration(ModerationRequest(input))
            .map(ModerationResponse::results)
            .mapNotNull(List<ModerationResults>::firstOrNull)
            .map(ModerationResults::flagged)
}
