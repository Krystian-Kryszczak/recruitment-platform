package krystian.kryszczak.recruitment.configuration.openai

import io.micronaut.context.annotation.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties("open-ai")
data class OpenAIConfiguration(
    var token: String? = null,
    var gptModel: String? = null,
    var textModerationModel: String? = null,
    var transcriptionModel: String? = null,
    var speechModel: String? = null,
    var speechVoice: String? = null,
    var language: String? = null,
    var defaultTimeout: Duration? = null
)
