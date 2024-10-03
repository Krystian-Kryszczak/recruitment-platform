package krystian.kryszczak.recruitment.model.being.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.identifier.business.BusinessIdentifiers

@Serdeable
@MappedEntity
@Introspected
data class Employer(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val name: String,
    val description: String? = null,
    val companyType: String? = null,
    val category: String? = null,
    val companySize: Int? = null,
    val website: String? = null,
    val facebook: String? = null,
    val instagram: String? = null,
    val linkedIn: String? = null,
    @param:Email override val email: String? = null,
    val phoneNumber: String? = null,
    @param:Max(100) val offices: Array<String>? = null,
    @param:Max(20) val techStack: Array<String>? = null,
    val country: String,
    val businessIdentifiers: Set<BusinessIdentifiers>? = null,
    val verified: Boolean = false,
    val banned: Boolean = false,
    val agreeToEmailMarketing: Boolean = false
) : Being<Employer>(id) {
    override fun isBanned(): Boolean = banned
    override fun isNotBanned(): Boolean = !isBanned()
    override fun copyBanned(): Employer = copy(banned = true)
    override fun copyUnbanned(): Employer = copy(banned = false)
    override fun filterTest(): Boolean = isNotBanned()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employer

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (companyType != other.companyType) return false
        if (category != other.category) return false
        if (companySize != other.companySize) return false
        if (website != other.website) return false
        if (facebook != other.facebook) return false
        if (instagram != other.instagram) return false
        if (linkedIn != other.linkedIn) return false
        if (email != other.email) return false
        if (phoneNumber != other.phoneNumber) return false
        if (offices != null) {
            if (other.offices == null) return false
            if (!offices.contentEquals(other.offices)) return false
        } else if (other.offices != null) return false
        if (techStack != null) {
            if (other.techStack == null) return false
            if (!techStack.contentEquals(other.techStack)) return false
        } else if (other.techStack != null) return false
        if (country != other.country) return false
        if (businessIdentifiers != other.businessIdentifiers) return false
        if (verified != other.verified) return false
        if (banned != other.banned) return false
        if (agreeToEmailMarketing != other.agreeToEmailMarketing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (companyType?.hashCode() ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (companySize ?: 0)
        result = 31 * result + (website?.hashCode() ?: 0)
        result = 31 * result + (facebook?.hashCode() ?: 0)
        result = 31 * result + (instagram?.hashCode() ?: 0)
        result = 31 * result + (linkedIn?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + (offices?.contentHashCode() ?: 0)
        result = 31 * result + (techStack?.contentHashCode() ?: 0)
        result = 31 * result + country.hashCode()
        result = 31 * result + (businessIdentifiers?.hashCode() ?: 0)
        result = 31 * result + verified.hashCode()
        result = 31 * result + banned.hashCode()
        result = 31 * result + agreeToEmailMarketing.hashCode()
        return result
    }
}
