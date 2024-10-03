package krystian.kryszczak.recruitment.mapper.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.mapper.being.AbstractBeingMapper
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import krystian.kryszczak.recruitment.service.management.moderation.ModerationService
import reactor.core.publisher.Mono

@Singleton
class DefaultEmployerMapper(
    passwordEncoder: PasswordEncoder,
    codeGenerator: ActivationCodeGenerator,
    private val moderationService: ModerationService
) : AbstractBeingMapper<Employer, EmployerDto, EmployerCreationForm, EmployerUpdateForm, EmployerCredentials, EmployerActivation>(
    passwordEncoder, codeGenerator
), EmployerMapper {
    override fun mapToRegisterActivation(form: EmployerCreationForm): Mono<EmployerActivation> = mapToRegisterActivation(form, ::EmployerActivation)

    override fun mapToCredentials(activation: EmployerActivation): EmployerCredentials = mapToCredentials(activation, ::EmployerCredentials)

    override fun mapToOriginItem(form: EmployerCreationForm, cascadeId: String?): Mono<Employer> = with(form) {
        moderationService.checkIfInputIsHarmful(form.extractPureTextContent().toString().trim(), cascadeId ?: "unknown")
            .map {
                Employer(
                    cascadeId,
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
                    phoneNumber,
                    offices,
                    techStack,
                    country,
                    businessIdentifiers,
                    false,
                    it,
                    agreeToEmailMarketing
                )
            }
    }

    override fun mapToOriginItem(actual: Employer, form: EmployerUpdateForm): Mono<Employer> = with(form) {
        moderationService.checkIfInputIsHarmful(form.extractPureTextContent(actual).toString().trim(), actual.id ?: "unknown")
            .map {
                Employer(
                    actual.id,
                    name ?: actual.name,
                    description ?: actual.description,
                    companyType ?: actual.companyType,
                    category ?: actual.category,
                    companySize ?: actual.companySize,
                    website ?: actual.website,
                    facebook ?: actual.facebook,
                    instagram ?: actual.instagram,
                    linkedIn ?: actual.linkedIn,
                    email ?: actual.email,
                    phoneNumber ?: actual.phoneNumber,
                    offices ?: actual.offices,
                    techStack ?: actual.techStack,
                    country ?: actual.country,
                    businessIdentifiers ?: actual.businessIdentifiers,
                    actual.verified,
                    it,
                    agreeToEmailMarketing ?: actual.agreeToEmailMarketing
                )
            }
    }

    override fun mapToDto(item: Employer): EmployerDto = with(item) {
        EmployerDto(
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
    }

    override fun mapToUpdateForm(item: Employer): EmployerUpdateForm = with(item) {
        EmployerUpdateForm(
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
            phoneNumber,
            offices,
            techStack,
            country,
            businessIdentifiers,
            agreeToEmailMarketing
        )
    }
}
