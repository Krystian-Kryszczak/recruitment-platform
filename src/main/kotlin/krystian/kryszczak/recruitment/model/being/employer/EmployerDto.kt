package krystian.kryszczak.recruitment.model.being.employer

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.BeingDto

@Serdeable
data class EmployerDto(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val companyType: String? = null,
    val category: String? = null,
    val companySize: Int? = null,
    val website: String? = null,
    val facebook: String? = null,
    val instagram: String? = null,
    val linkedIn: String? = null,
    val email: String? = null,
    val offices: Array<String>? = null,
    val techStack: Array<String>? = null
) : BeingDto<Employer, EmployerDto> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmployerDto

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
        if (offices != null) {
            if (other.offices == null) return false
            if (!offices.contentEquals(other.offices)) return false
        } else if (other.offices != null) return false
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
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (offices?.contentHashCode() ?: 0)
        result = 31 * result + (techStack?.contentHashCode() ?: 0)
        return result
    }
}
