package krystian.kryszczak.recruitment.model.being.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm

@Serdeable
@Introspected
class EmployerUpdateForm(
    private val name: String,
    private val description: String? = null,
    private val companyType: String? = null,
    private val category: String? = null,
    private val companySize: Int? = null,
    private val website: String? = null,
    private val facebook: String? = null,
    private val instagram: String? = null,
    private val linkedIn: String? = null,
    private val email: String? = null,
    @param:Max(100) private val offices: Array<String>? = null,
    @param:Max(20) private val techStack: Array<String>? = null,
    private val agreeToEmailMarketing: Boolean = false
) : BeingUpdateForm<Employer, EmployerUpdateForm> {
    override fun transform(id: String, metadata: Map<String, Any>): Employer = Employer(
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
        techStack,
        agreeToEmailMarketing
    )

    companion object : BeingUpdateForm.Mapper<Employer, EmployerUpdateForm> {
        override fun from(item: Employer) = EmployerUpdateForm(
            item.name,
            item.description,
            item.companyType,
            item.category,
            item.companySize,
            item.website,
            item.facebook,
            item.instagram,
            item.linkedIn,
            item.email,
            item.offices,
            item.techStack,
            item.agreeToEmailMarketing
        )
    }
}
