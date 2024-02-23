package krystian.kryszczak.recruitment.model.security.refresh

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item
import java.time.Instant

@Serdeable
@MappedEntity
@Introspected
data class RefreshToken(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val username: String? = null,
    val refreshToken: String? = null,
    val revoked: Boolean? = null,
    val dateCreated: Instant? = null
): Item(id)
