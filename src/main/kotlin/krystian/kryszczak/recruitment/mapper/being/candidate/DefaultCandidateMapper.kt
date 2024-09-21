package krystian.kryszczak.recruitment.mapper.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.mapper.being.AbstractBeingMapper
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.service.management.moderation.ModerationService
import reactor.core.publisher.Mono

@Singleton
class DefaultCandidateMapper(
    passwordEncoder: PasswordEncoder,
    codeGenerator: ActivationCodeGenerator,
    private val moderationService: ModerationService
) : AbstractBeingMapper<Candidate, CandidateDto, CandidateCreationForm, CandidateUpdateForm, CandidateCredentials, CandidateActivation>(
    passwordEncoder, codeGenerator
), CandidateMapper {
    override fun mapToRegisterActivation(form: CandidateCreationForm): Mono<CandidateActivation> = mapToRegisterActivation(form, ::CandidateActivation)

    override fun mapToCredentials(activation: CandidateActivation): CandidateCredentials = mapToCredentials(activation, ::CandidateCredentials)

    override fun mapToOriginItem(form: CandidateCreationForm, cascadeId: String?): Mono<Candidate> = with(form) {
        moderationService.checkIfInputIsHarmful(form.extractPureTextContent().toString().trim(), cascadeId ?: "unknown")
            .map {
                Candidate(
                    cascadeId,
                    email,
                    firstName,
                    lastName,
                    phoneNumber,
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
                    it,
                    agreeToEmailMarketing
                )
            }
    }

    override fun mapToOriginItem(actual: Candidate, form: CandidateUpdateForm): Mono<Candidate> = with(form) {
        moderationService.checkIfInputIsHarmful(form.extractPureTextContent(actual).toString().trim(), actual.id ?: "unknown")
            .map {
                Candidate(
                    actual.id,
                    email ?: actual.email,
                    firstName ?: actual.firstName,
                    lastName ?: actual.lastName,
                    phoneNumber ?: actual.phoneNumber,
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
                    it,
                    agreeToEmailMarketing ?: actual.agreeToEmailMarketing
                )
            }
    }

    override fun mapToDto(item: Candidate): CandidateDto = with(item) {
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
    }

    override fun mapToUpdateForm(item: Candidate): CandidateUpdateForm = with(item) {
        CandidateUpdateForm(
            email,
            firstName,
            lastName,
            phoneNumber,
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
    }
}
