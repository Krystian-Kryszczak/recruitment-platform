package krystian.kryszczak.recruitment.model.moderation.response

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class ModerationResponse(val id: String, val model: String, val results: List<ModerationResults>)
