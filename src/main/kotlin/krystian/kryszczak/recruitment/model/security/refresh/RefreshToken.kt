package krystian.kryszczak.recruitment.model.security.refresh

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import krystian.kryszczak.recruitment.model.Item
import java.time.Instant

@Serdeable
@MappedEntity
@Introspected
data class RefreshToken(
    @field:Id @GeneratedValue override val id: String? = null,
    @NotBlank val username: String,
    @NotBlank val refreshToken: String,
    val revoked: Boolean,
    @DateCreated val dateCreated: Instant? = null
) : Item(id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RefreshToken

        if (id != other.id) return false
        if (username != other.username) return false
        if (refreshToken != other.refreshToken) return false
        if (revoked != other.revoked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + username.hashCode()
        result = 31 * result + refreshToken.hashCode()
        result = 31 * result + revoked.hashCode()
        return result
    }
}
