package krystian.kryszczak.recruitment.service.moderation

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.client.openai.OpenAIHttpClient
import krystian.kryszczak.recruitment.model.moderation.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResults
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class ModerationServiceTest(moderationService: ModerationService) : FreeSpec({
    "moderation service test" - {
        "check if input is harmful" {
            val phrases = mapOf(
                "Some harmful phrase." to true,
                "Hello world!" to false
            )

            for ((phrase, excepted) in phrases) {
                moderationService.checkIfInputIsHarmful(phrase, uniqueId())
                    .block()
                    .shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }
    }
}) {
    @MockBean(OpenAIHttpClient::class)
    fun openAIHttpClient(): OpenAIHttpClient {
        val httpClient = mockk<OpenAIHttpClient>()

        every { httpClient.createModeration(any()) } returns Mono.just(
            ModerationResponse(
                "modr-" + uniqueId(),
                "text-moderation-007",
                listOf(ModerationResults(false, mapOf(), mapOf()))
            )
        )

        every { httpClient.createModeration(ModerationRequest("Some harmful phrase.")) } returns Mono.just(
            ModerationResponse(
                "modr-" + uniqueId(),
                "text-moderation-007",
                listOf(ModerationResults(true, mapOf(), mapOf()))
            )
        )

        return httpClient
    }
}
