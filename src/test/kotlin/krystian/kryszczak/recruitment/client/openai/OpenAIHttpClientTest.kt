package krystian.kryszczak.recruitment.client.openai

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import krystian.kryszczak.recruitment.model.moderation.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResults
import krystian.kryszczak.test.util.WireMockUtils.wireMockEnv

class OpenAIHttpClientTest : FreeSpec({
    "open ai http client test" - {
        "create moderation" {
            wireMockEnv { context ->
                // given
                val httpClient = context.getBean(OpenAIHttpClient::class.java)
                val request = ModerationRequest("Hello world!")

                // when
                val result = httpClient.createModeration(request)
                    .block()

                // then
                with(result.shouldNotBeNull()) {
                    id shouldBe "modr-XXXXX"
                    model shouldBe "text-moderation-007"
                    results.shouldHaveSize(1).first() shouldBe ModerationResults(
                        true,
                        mapOf(
                            "sexual" to false,
                            "hate" to false,
                            "harassment" to false,
                            "self-harm" to false,
                            "sexual/minors" to false,
                            "hate/threatening" to false,
                            "violence/graphic" to false,
                            "self-harm/intent" to false,
                            "self-harm/instructions" to false,
                            "harassment/threatening" to true,
                            "violence" to true
                        ),
                        mapOf(
                            "sexual" to 1.2282071e-06,
                            "hate" to 0.010696256,
                            "harassment" to 0.29842457,
                            "self-harm" to 1.5236925e-08,
                            "sexual/minors" to 5.7246268e-08,
                            "hate/threatening" to 0.0060676364,
                            "violence/graphic" to 4.435014e-06,
                            "self-harm/intent" to 8.098441e-10,
                            "self-harm/instructions" to 2.8498655e-11,
                            "harassment/threatening" to 0.63055265,
                            "violence" to 0.99011886
                        )
                    )
                }
            }
        }
    }
})
