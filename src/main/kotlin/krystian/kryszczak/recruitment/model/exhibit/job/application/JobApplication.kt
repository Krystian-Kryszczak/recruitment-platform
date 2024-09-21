package krystian.kryszczak.recruitment.model.exhibit.job.application

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.exhibit.Exhibit
import java.time.Instant

@Serdeable
@MappedEntity
data class JobApplication(
    @field:Id @GeneratedValue override val id: String? = null,
    val offerId: String,
    val candidateId: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val messageToRecruiter: String? = null,
    val cvFileReferenceId: String? = null,
    val banned: Boolean = false,
    @DateCreated val dateCreated: Instant? = null
) : Exhibit<JobApplication>(id) {
    override fun isBanned(): Boolean = banned
    override fun isNotBanned(): Boolean = !isBanned()
    override fun copyBanned(): JobApplication = copy(banned = true)
    override fun copyUnbanned(): JobApplication = copy(banned = false)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobApplication

        if (id != other.id) return false
        if (offerId != other.offerId) return false
        if (candidateId != other.candidateId) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (messageToRecruiter != other.messageToRecruiter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + offerId.hashCode()
        result = 31 * result + (candidateId?.hashCode() ?: 0)
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (messageToRecruiter?.hashCode() ?: 0)
        return result
    }
}
