package krystian.kryszczak.recruitment.model.being.candidate

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.constant.Sex

@Serdeable
@Introspected
class CandidateDto(
    val id: String? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val messageToTheEmployer: String? = null,
    val linkedInProfile: String? = null,
    val gitHubProfile: String? = null,
    val workHistory: Array<String>? = null,
    val position: String? = null,
    val yearsOfExperience: Byte = 0,
    val categories: Array<String>? = null,
    val skills: Array<String>? = null,
    val employmentTypeAndSalary: Map<String, String>? = null,
    val locations: Array<String>? = null,
    val englishLevel: Int = 0,
    val sex: Sex? = null
) : BeingDto<Candidate, CandidateDto> {
    companion object : BeingDto.Mapper<Candidate, CandidateDto> {
        override fun from(item: Candidate) = CandidateDto(
            item.id,
            item.email,
            item.firstName,
            item.lastName,
            item.messageToTheEmployer,
            item.linkedInProfile,
            item.gitHubProfile,
            item.workHistory,
            item.position,
            item.yearsOfExperience,
            item.categories,
            item.skills,
            item.employmentTypeAndSalary,
            item.locations,
            item.englishLevel,
            item.sex
        )
    }
}
