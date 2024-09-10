package krystian.kryszczak.recruitment.mapper.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import org.bson.types.ObjectId
import reactor.core.publisher.Mono

@Singleton
class DefaultEmployerMapper : EmployerMapper {
    override fun mapToOriginItem(activation: EmployerActivation): Mono<Employer> = with(activation) {
        mapToOriginItem(creationForm, id ?: ObjectId.get().toHexString())
    }

    override fun mapToCredentials(activation: EmployerActivation): EmployerCredentials = with(activation) {
        EmployerCredentials(null, identity, encodedPassword)
    }

    override fun mapToOriginItem(form: EmployerCreationForm, doerId: String): Mono<Employer> = with(form) {
        Mono.just(
            Employer(
                doerId,
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
                false,
                agreeToEmailMarketing
            )
        )
    }

    override fun mapToOriginItem(actual: Employer, form: EmployerUpdateForm): Mono<Employer> = with(form) {
        Mono.just(
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
                offices ?: actual.offices,
                techStack ?: actual.techStack,
                actual.banned,
                agreeToEmailMarketing ?: actual.agreeToEmailMarketing
            )
        )
    }

    override fun mapToDto(item: Employer): Mono<EmployerDto> = with(item) {
        Mono.just(
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
        )
    }

    override fun mapToUpdateForm(item: Employer): Mono<EmployerUpdateForm> = with(item) {
        Mono.just(
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
                offices,
                techStack,
                agreeToEmailMarketing
            )
        )
    }
}
