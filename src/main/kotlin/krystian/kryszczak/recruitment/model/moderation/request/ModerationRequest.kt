package krystian.kryszczak.recruitment.model.moderation.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Serdeable
@Introspected
class ModerationRequest(@NotBlank val input: String, val model: String? = null) {
    override fun hashCode(): Int {
        var result = input.hashCode()
        result = 31 * result + (model?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModerationRequest

        if (input != other.input) return false
        if (model != other.model) return false

        return true
    }
}
