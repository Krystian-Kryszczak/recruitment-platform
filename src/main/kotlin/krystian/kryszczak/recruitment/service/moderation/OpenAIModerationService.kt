package krystian.kryszczak.recruitment.service.moderation

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.client.openai.OpenAIHttpClient
import krystian.kryszczak.recruitment.database.mongodb.repository.metric.moderation.MetricModerationRepository
import krystian.kryszczak.recruitment.model.metric.moderation.MetricModeration
import krystian.kryszczak.recruitment.model.moderation.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResults
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Singleton
class OpenAIModerationService(
    private val httpClient: OpenAIHttpClient,
    private val repository: MetricModerationRepository
): ModerationService {
    override fun checkIfInputIsHarmful(input: String, id: String): Mono<Boolean> =
        checkIfInputIsHarmful(ModerationRequest(input), id)

    private fun checkIfInputIsHarmful(request: ModerationRequest, id: String): Mono<Boolean> =
        httpClient.createModeration(request)
            .doOnNext {
                repository.save(MetricModeration(null, id, request, it))
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe()
            }.map(ModerationResponse::results)
            .mapNotNull(List<ModerationResults>::firstOrNull)
            .map(ModerationResults::flagged)
}
