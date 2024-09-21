package krystian.kryszczak.recruitment.model.being.employer

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Pattern
import krystian.kryszczak.recruitment.controller.api.PHONE_NUMBER_REGEX
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.identifier.business.BusinessIdentifiers

@Serdeable
data class EmployerCreationForm(
    @param:Email override val email: String,
    val name: String,
    val description: String? = null,
    val companyType: String? = null,
    val category: String? = null,
    val companySize: Int? = null,
    val website: String? = null,
    val facebook: String? = null,
    val instagram: String? = null,
    val linkedIn: String? = null,
    @param:Pattern(regexp = PHONE_NUMBER_REGEX) val phoneNumber: String? = null,
    @param:Max(100) val offices: Array<String>? = null,
    @param:Max(20) val techStack: Array<String>? = null,
    val country: String,
    val businessIdentifiers: Set<BusinessIdentifiers>? = null,
    val agreeToEmailMarketing: Boolean = false,
    override val password: String,
    override val acceptRules: Boolean
) : BeingCreationForm<Employer, EmployerCreationForm>(email, password, acceptRules) {
    override fun extractPureTextContent(): StringBuilder = with(StringBuilder()) {
        append(name).append(" ")
        append(email)
        description?.let { append(" ").append(it) }
        companyType?.let { append(" ").append(it) }
        category?.let { append(" ").append(it) }
        website?.let { append(" ").append(it) }
        facebook?.let { append(" ").append(it) }
        instagram?.let { append(" ").append(it) }
        linkedIn?.let { append(" ").append(it) }
        phoneNumber?.let { append(" ").append(it) }
        offices?.forEach { append(" ").append(it) }
        techStack?.forEach { append(" ").append(it) }
        country.let { append(" ").append(it) }
        businessIdentifiers?.forEach { append(" ").append(it) }
        this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmployerCreationForm

        if (email != other.email) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (companyType != other.companyType) return false
        if (category != other.category) return false
        if (companySize != other.companySize) return false
        if (website != other.website) return false
        if (facebook != other.facebook) return false
        if (instagram != other.instagram) return false
        if (linkedIn != other.linkedIn) return false
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
        if (agreeToEmailMarketing != other.agreeToEmailMarketing) return false
        if (password != other.password) return false
        if (acceptRules != other.acceptRules) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (companyType?.hashCode() ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (companySize ?: 0)
        result = 31 * result + (website?.hashCode() ?: 0)
        result = 31 * result + (facebook?.hashCode() ?: 0)
        result = 31 * result + (instagram?.hashCode() ?: 0)
        result = 31 * result + (linkedIn?.hashCode() ?: 0)
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + (offices?.contentHashCode() ?: 0)
        result = 31 * result + (techStack?.contentHashCode() ?: 0)
        result = 31 * result + country.hashCode()
        result = 31 * result + (businessIdentifiers?.hashCode() ?: 0)
        result = 31 * result + agreeToEmailMarketing.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + acceptRules.hashCode()
        return result
    }
}
