package krystian.kryszczak.recruitment.model.account.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.account.Account

@Serdeable
@MappedEntity
@Introspected
data class EmployerAccount(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val name: String,
    val description: String?,
    val companyType: String?,
    val category: String?,
    val companySize: Int?,
    val website: String?,
    val facebook: String?,
    val instagram: String?,
    val linkedIn: String?,
    val offices: Array<String>?,
    val activeOffersIds: Array<String>?,
    val techStack: Array<String>?
): Account(id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmployerAccount

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
        if (offices != null) {
            if (other.offices == null) return false
            if (!offices.contentEquals(other.offices)) return false
        } else if (other.offices != null) return false
        if (activeOffersIds != null) {
            if (other.activeOffersIds == null) return false
            if (!activeOffersIds.contentEquals(other.activeOffersIds)) return false
        } else if (other.activeOffersIds != null) return false
        if (techStack != null) {
            if (other.techStack == null) return false
            if (!techStack.contentEquals(other.techStack)) return false
        } else if (other.techStack != null) return false

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
        result = 31 * result + (offices?.contentHashCode() ?: 0)
        result = 31 * result + (activeOffersIds?.contentHashCode() ?: 0)
        result = 31 * result + (techStack?.contentHashCode() ?: 0)
        return result
    }
}
