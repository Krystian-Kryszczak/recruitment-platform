package krystian.kryszczak.recruitment.model.being.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.BeingDto

@Serdeable
@Introspected
class EmployerDto(
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
    companion object : BeingDto.Mapper<Employer, EmployerDto> {
        override fun from(item: Employer) = EmployerDto(
            item.id,
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
            item.techStack
        )
    }
}
