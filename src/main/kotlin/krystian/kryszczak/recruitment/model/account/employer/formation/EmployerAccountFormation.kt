package krystian.kryszczak.recruitment.model.account.employer.formation

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.account.employer.EmployerAccount

@Serdeable
@Introspected
class EmployerAccountFormation(
    private val name: String,
    private val description: String?,
    private val companyType: String?,
    private val category: String?,
    private val companySize: Int?,
    private val website: String?,
    private val facebook: String?,
    private val instagram: String?,
    private val linkedIn: String?,
    @param:Max(100) private val offices: Array<String>?,
    @param:Max(20) private val techStack: Array<String>?
) : Formation<EmployerAccount>() {
    override fun format(id: String?) = EmployerAccount(
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
        offices,
        techStack
    )
}
