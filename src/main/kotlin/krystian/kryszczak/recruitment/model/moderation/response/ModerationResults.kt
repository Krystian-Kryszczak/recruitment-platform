package krystian.kryszczak.recruitment.model.moderation.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ModerationResults(val flagged: Boolean, val categories: Map<String, Boolean>, val categoryScores: Map<String, Double>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModerationResults

        if (flagged != other.flagged) return false
        if (categories != other.categories) return false
        if (categoryScores != other.categoryScores) return false

        return true
    }

    override fun hashCode(): Int {
        var result = flagged.hashCode()
        result = 31 * result + categories.hashCode()
        result = 31 * result + categoryScores.hashCode()
        return result
    }

    override fun toString(): String {
        return "ModerationResults(flagged=$flagged, categories=$categories, categoryScores=$categoryScores)"
    }
}
