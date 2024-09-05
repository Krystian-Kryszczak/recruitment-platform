package krystian.kryszczak.recruitment.model.job.application

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.job.Exhibit
import krystian.kryszczak.recruitment.model.moderation.response.ModerationResponse
import java.time.Instant

@Serdeable
@MappedEntity
@Introspected
data class JobApplication(
    @field:Id @GeneratedValue override val id: String? = null,
    val offerId: String,
    val candidateId: String,
    val cvFileId: String,
    val messageToRecruiter: String? = null,
    val moderation: ModerationResponse? = null,
    val banned: Boolean = false,
    @DateCreated val dateCreated: Instant? = null
) : Exhibit(id, banned) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobApplication

        if (id != other.id) return false
        if (offerId != other.offerId) return false
        if (candidateId != other.candidateId) return false
        if (cvFileId != other.cvFileId) return false
        if (messageToRecruiter != other.messageToRecruiter) return false
        if (moderation != other.moderation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + offerId.hashCode()
        result = 31 * result + candidateId.hashCode()
        result = 31 * result + cvFileId.hashCode()
        result = 31 * result + messageToRecruiter.hashCode()
        result = 31 * result + (moderation?.hashCode() ?: 0)
        return result
    }
}
