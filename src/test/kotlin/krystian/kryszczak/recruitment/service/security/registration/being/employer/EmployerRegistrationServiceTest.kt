package krystian.kryszczak.recruitment.service.security.registration.being.employer

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.employer.EmployerActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.employer.EmployerMapper
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationServiceTest
import krystian.kryszczak.test.mock.employerMock

@MicronautTest(transactional = false)
class EmployerRegistrationServiceTest(registrationService: EmployerRegistrationService) :
BeingRegistrationServiceTest<Employer, EmployerCreationForm, EmployerUpdateForm, EmployerDto, EmployerCredentials, EmployerActivation>(
    registrationService,
    listOf(
        Pair(
            EmployerCreationForm(
                "support@e-corp.com",
                "E-Corp",
                country = "USA",
                password = "EvilCorp#123",
                acceptRules = true
            ),
            true
        )
    ),
    listOf(
        Triple("john.smith@e-corp.com", "<code>", true),
        Triple("john.smith@e-corp.com", "<wrong-code>", false),
        Triple("eliot.smith@e-corp.com", "<code>", true),
        Triple("eliot.smith@e-corp.com", "<wrong-code>", false)
    )
) {
    @MockBean(EmployerActivationRepository::class)
    fun activationRepository(): EmployerActivationRepository =
        beingActivationRepository<EmployerActivation, EmployerActivationRepository>(::activation)

    private fun activation(identity: String, code: String) = EmployerActivation(
        null,
        code,
        identity,
        EmployerCreationForm(
            "support@e-corp.com",
            "E-Corp",
            country = "USA",
            password = "EvilCorp",
            acceptRules = true
        ),
        "<encoded-password>"
    )

    @MockBean(EmployerCredentialsRepository::class)
    fun credentialsRepository(): EmployerCredentialsRepository =
        beingCredentialsRepository<EmployerCredentials, EmployerCredentialsRepository>()

    @MockBean(EmployerService::class)
    fun employerService(): EmployerService = beingService<Employer, EmployerService>()

    @MockBean(EmployerMapper::class)
    fun employerMapper(): EmployerMapper = beingMapper<EmployerCreationForm, EmployerActivation, EmployerMapper>(
        employerMock,
        ::activation,
        EmployerCredentials()
    )

    @MockBean(SmtpMailerService::class)
    override fun smtpMailerService(): SmtpMailerService = super.smtpMailerService()
}
