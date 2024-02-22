package krystian.kryszczak.recruitment.configuration.openai

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.micronaut.context.ApplicationContext
import org.junit.jupiter.api.Assertions
import java.time.Duration

class OpenAIConfigurationTest : StringSpec({
    "Open AI configuration test" {
        val items: MutableMap<String, Any> = HashMap()
        items["open-ai.token"] = "sk-test-token"
        items["open-ai.gpt-model"] = "gpt-3.5-turbo"
        items["text-moderation-model"] = "text-moderation-stable"
        items["open-ai.transcription-model"] = "whisper-1"
        items["open-ai.speech-model"] = "tts-1"
        items["open-ai.speech-voice"] = "alloy"
        items["open-ai.language"] = "pl"
        items["open-ai.default-timeout"] = "60s"

        val ctx = ApplicationContext.run(items)
        val openAIConfiguration: OpenAIConfiguration = ctx.getBean(OpenAIConfiguration::class.java)

        val token: String = openAIConfiguration.token.shouldNotBeNull()
        val gptModel: String = openAIConfiguration.gptModel.shouldNotBeNull()
        val textModerationModel: String = openAIConfiguration.textModerationModel.shouldNotBeNull()
        val transcriptionModel: String = openAIConfiguration.transcriptionModel.shouldNotBeNull()
        val language: String = openAIConfiguration.language.shouldNotBeNull()
        val defaultTimeout: Duration = openAIConfiguration.defaultTimeout.shouldNotBeNull()

        Assertions.assertEquals("sk-test-token", token)
        Assertions.assertEquals("gpt-3.5-turbo", gptModel)
        Assertions.assertEquals("text-moderation-stable", textModerationModel)
        Assertions.assertEquals("whisper-1", transcriptionModel)
        Assertions.assertEquals("pl", language)
        Assertions.assertEquals(Duration.ofSeconds(60), defaultTimeout)
        ctx.close()
    }
})
