package krystian.kryszczak.recruitment.model.being.employer.formation

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.employer.Employer

@Serdeable
@Introspected
data class EmployerFormation(
    val name: String,
    val description: String?,
    val companyType: String?,
    val category: String?,
    val companySize: Int?,
    val website: String?,
    val facebook: String?,
    val instagram: String?,
    val linkedIn: String?,
    val email: String?,
    @param:Max(100) val offices: Array<String>?,
    @param:Max(20) val techStack: Array<String>?
) : Formation<Employer>() {
    override fun format(id: String?) = Employer(
        id,
        name,
        description,
        companyType,
        category,
        companySize,
        website,
        facebook,
        instagram,
        linkedIn,
        email,
        offices,
        techStack
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmployerFormation

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
        var result = name.hashCode()
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
