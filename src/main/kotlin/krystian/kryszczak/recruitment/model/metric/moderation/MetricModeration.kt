package krystian.kryszczak.recruitment.model.metric.moderation

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.moderation.request.ModerationRequest
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse
import java.time.Instant

@Serdeable
@MappedEntity
data class MetricModeration(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val refersToId: String,
    val request: ModerationRequest,
    val response: ModerationResponse,
    @DateCreated val dateCreated: Instant? = null
): Item(id)
