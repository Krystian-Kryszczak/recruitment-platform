package krystian.kryszczak.recruitment.model.being.candidate

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.constant.Sex

@Serdeable
@Introspected
class CandidateUpdateForm(
    private val email: String,
    private val firstName: String,
    private val lastName: String,
    @param:Max(1000) private val messageToTheEmployer: String? = null,
    private val linkedInProfile: String? = null,
    private val gitHubProfile: String? = null,
    @param:Max(10) private val workHistory: Array<String>? = null,
    private val position: String? = null,
    @param:Max(50) private val yearsOfExperience: Byte = 0,
    @param:Max(4) private val categories: Array<String>? = null,
    @param:Max(12) private val skills: Array<String>? = null,
    @param:Max(5) private val employmentTypeAndSalary: Map<String, String>? = null,
    @param:Max(10) private val locations: Array<String>? = null,
    @param:PositiveOrZero @param:Max(7) private val englishLevel: Int = 0,
    private val sex: Sex? = null,
    private val agreeToEmailMarketing: Boolean = false
) : BeingUpdateForm<Candidate, CandidateUpdateForm> {
    override fun transform(id: String, metadata: Map<String, Any>): Candidate = Candidate(
        id,
        email,
        firstName,
        lastName,
        messageToTheEmployer,
        linkedInProfile,
        gitHubProfile,
        workHistory,
        position,
        yearsOfExperience,
        categories,
        skills,
        employmentTypeAndSalary,
        locations,
        englishLevel,
        sex,
        agreeToEmailMarketing
    )

    companion object : BeingUpdateForm.Mapper<Candidate, CandidateUpdateForm> {
        override fun from(item: Candidate) = CandidateUpdateForm(
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
            item.sex,
            item.agreeToEmailMarketing
        )
    }
}
