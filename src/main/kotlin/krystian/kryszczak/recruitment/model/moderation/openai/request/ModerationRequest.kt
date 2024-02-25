package krystian.kryszczak.recruitment.model.moderation.openai.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Serdeable
@Introspected
class ModerationRequest(@NotBlank val input: String, val model: String?)
