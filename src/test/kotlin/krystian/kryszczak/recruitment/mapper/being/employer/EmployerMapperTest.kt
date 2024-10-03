package krystian.kryszczak.recruitment.mapper.being.employer

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.mapper.being.BeingMapperTest
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.management.moderation.ModerationService

@MicronautTest(transactional = false)
class EmployerMapperTest(employerMapper: EmployerMapper) :
BeingMapperTest<Employer, EmployerDto, EmployerCreationForm, EmployerUpdateForm, EmployerCredentials, EmployerActivation>(
    employerMapper,
    Triple(
        EmployerCreationForm(
            "contact@e-corp.com",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            setOf(),
            true,
            "password",
            true
        ),
        "<doer-id>",
        Employer(
            "<doer-id>",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            setOf(),
            banned = false,
            agreeToEmailMarketing = true
        )
    ),
    Triple(
        Employer(
            "<employer-id>",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            banned = false,
            agreeToEmailMarketing = true
        ),
        EmployerUpdateForm(
            description = "Hello friend!",
            companySize = 1000,
            website = "https://mrrobot.fandom.com/wiki/Mr._Robot",
            agreeToEmailMarketing = false
        ),
        Employer(
            "<employer-id>",
            "E-Corp",
            "Hello friend!",
            "Conglomerate",
            "FinTech",
            1000,
            "https://mrrobot.fandom.com/wiki/Mr._Robot",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            banned = false,
            agreeToEmailMarketing = false
        )
    ),
    Pair(
        Employer(
            "<employer-id>",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            banned = false,
            agreeToEmailMarketing = true
        ),
        EmployerDto(
            "<employer-id>",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            arrayOf(),
            arrayOf()
        )
    ),
    Pair(
        Employer(
            "<employer-id>",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            setOf(),
            banned = false,
            agreeToEmailMarketing = true
        ),
        EmployerUpdateForm(
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            setOf(),
            true
        )
    ),
    Pair(
        EmployerActivation(
            "<employer-id>",
            "code",
            "contact@e-corp.com",
            EmployerCreationForm(
                "contact@e-corp.com",
                "E-Corp",
                "Hello world!",
                "Conglomerate",
                "FinTech",
                5,
                "https://mrrobot.fandom.com/wiki/E-Corp",
                "facebook.com",
                "instagram.com",
                "linkedin.com",
                "123 456 789",
                arrayOf(),
                arrayOf(),
                "USA",
                setOf(),
                true,
                "password",
                true
            ),
            "<encoded-password>"
        ),
        Employer(
            "<employer-id>",
            "E-Corp",
            "Hello world!",
            "Conglomerate",
            "FinTech",
            5,
            "https://mrrobot.fandom.com/wiki/E-Corp",
            "facebook.com",
            "instagram.com",
            "linkedin.com",
            "contact@e-corp.com",
            "123 456 789",
            arrayOf(),
            arrayOf(),
            "USA",
            setOf(),
            banned = false,
            agreeToEmailMarketing = true
        )
    ),
    Pair(
        EmployerActivation(
            "<employer-activation-id>",
            "<employer-activation-code>",
            "contact@e-corp.com",
            EmployerCreationForm(
                "contact@e-corp.com",
                "E-Corp",
                "Hello world!",
                "Conglomerate",
                "FinTech",
                5,
                "https://mrrobot.fandom.com/wiki/E-Corp",
                "facebook.com",
                "instagram.com",
                "linkedin.com",
                "123 456 789",
                arrayOf(),
                arrayOf(),
                "USA",
                setOf(),
                true,
                "password",
                true
            ),
            "<encoded-password>"
        ),
        EmployerCredentials(
            null,
            "contact@e-corp.com",
            "<encoded-password>"
        )
    ),
    { item, id -> item.copy(id = id) }
) {
    @MockBean(ModerationService::class)
    override fun moderationService(): ModerationService = super.moderationService()
}
