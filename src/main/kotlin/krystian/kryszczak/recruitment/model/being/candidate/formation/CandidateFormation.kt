package krystian.kryszczak.recruitment.model.being.candidate.formation

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PositiveOrZero
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.candidate.Candidate

@Serdeable
@Introspected
class CandidateFormation(
    private val firstName: String,
    private val lastName: String,
    @param:Max(1000) private val messageToTheEmployer: String,
    private val linkedInProfile: String,
    private val gitHubProfile: String,
    @param:Max(10) private val workHistory: Array<String>?,
    private val position: String,
    @param:Max(50) private val yearsOfExperience: Byte,
    @param:Max(4) private val categories: Array<String>?,
    @param:Max(12) private val skills: Array<String>?,
    @param:Max(5) private val employmentTypeAndSalary: Array<String>?,
    @param:Max(10) private val locations: Array<String>?,
    @param:PositiveOrZero @param:Max(7) private val englishLevel: Int,
    private val sex: Boolean
): Formation<Candidate>() {
    override fun format(id: String?): Candidate = Candidate(
        id,
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
        sex
    )
}
