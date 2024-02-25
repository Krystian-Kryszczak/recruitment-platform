package krystian.kryszczak.recruitment.model.moderation.openai.response

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class ModerationResults(val flagged: Boolean, val categories: Map<String, Boolean>, val categoryScores: Map<String, Double>)
