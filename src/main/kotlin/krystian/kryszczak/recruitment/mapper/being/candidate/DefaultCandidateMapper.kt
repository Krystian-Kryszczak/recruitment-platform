package krystian.kryszczak.recruitment.mapper.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import org.bson.types.ObjectId
import reactor.core.publisher.Mono

@Singleton
class DefaultCandidateMapper : CandidateMapper {
    override fun mapToOriginItem(activation: CandidateActivation): Mono<Candidate> = with(activation) {
        mapToOriginItem(creationForm, id ?: ObjectId.get().toHexString())
    }

    override fun mapToCredentials(activation: CandidateActivation): CandidateCredentials = with(activation) {
        CandidateCredentials(null, identity, encodedPassword)
    }

    override fun mapToOriginItem(form: CandidateCreationForm, doerId: String): Mono<Candidate> = with(form) {
        Mono.just(
            Candidate(
                doerId,
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
                false,
                agreeToEmailMarketing
            )
        )
    }

    override fun mapToOriginItem(actual: Candidate, form: CandidateUpdateForm): Mono<Candidate> = with(form) {
        Mono.just(
            Candidate(
                actual.id,
                email ?: actual.email,
                firstName ?: actual.firstName,
                lastName ?: actual.lastName,
                messageToTheEmployer ?: actual.messageToTheEmployer,
                linkedInProfile ?: actual.linkedInProfile,
                gitHubProfile ?: actual.gitHubProfile,
                workHistory ?: actual.workHistory,
                position ?: actual.position,
                yearsOfExperience ?: actual.yearsOfExperience,
                categories ?: actual.categories,
                skills ?: actual.skills,
                employmentTypeAndSalary ?: actual.employmentTypeAndSalary,
                locations ?: actual.locations,
                englishLevel ?: actual.englishLevel,
                sex ?: actual.sex,
                actual.banned,
                agreeToEmailMarketing ?: actual.agreeToEmailMarketing
            )
        )
    }

    override fun mapToDto(item: Candidate): Mono<CandidateDto> = with(item) {
        Mono.just(
            CandidateDto(
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
                sex
            )
        )
    }

    override fun mapToUpdateForm(item: Candidate): Mono<CandidateUpdateForm> = with(item) {
        Mono.just(
            CandidateUpdateForm(
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
        )
    }
}
