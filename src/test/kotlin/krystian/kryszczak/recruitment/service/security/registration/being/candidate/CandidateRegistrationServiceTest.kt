package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.candidate.CandidateActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.candidate.CandidateCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.candidate.CandidateMapper
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.registration.being.BeingRegistrationServiceTest
import krystian.kryszczak.test.mock.candidateMock

@MicronautTest(transactional = false)
class CandidateRegistrationServiceTest(registrationService: CandidateRegistrationService) :
BeingRegistrationServiceTest<Candidate, CandidateCreationForm, CandidateUpdateForm, CandidateDto, CandidateCredentials, CandidateActivation>(
    registrationService,
    listOf(
        Pair(
            CandidateCreationForm(
                "john.smith@gmail.com",
                "john",
                "smith",
                password = "HelloFriend#123",
                acceptRules = true
            ),
            true
        )
    ),
    listOf(
        Triple("john.smith@gmail.com", "<code>", true),
        Triple("john.smith@gmail.com", "<wrong-code>", false),
        Triple("eliot.smith@gmail.com", "<code>", true),
        Triple("eliot.smith@gmail.com", "<wrong-code>", false)
    )
) {
    @MockBean(CandidateActivationRepository::class)
    fun activationRepository(): CandidateActivationRepository =
        beingActivationRepository<CandidateActivation, CandidateActivationRepository>(::activation)

    private fun activation(identity: String, code: String) = CandidateActivation(
        null,
        code,
        identity,
        CandidateCreationForm(
            "john.smith@gmail.com",
            "John",
            "Smith",
            password = "HelloFriend!",
            acceptRules = true
        ),
        "<encoded-password>"
    )

    @MockBean(CandidateCredentialsRepository::class)
    fun credentialsRepository(): CandidateCredentialsRepository =
        beingCredentialsRepository<CandidateCredentials, CandidateCredentialsRepository>()

    @MockBean(CandidateService::class)
    fun candidateService(): CandidateService = beingService<Candidate, CandidateService>()

    @MockBean(CandidateMapper::class)
    fun candidateMapper(): CandidateMapper = beingMapper<CandidateCreationForm, CandidateActivation, CandidateMapper>(
        candidateMock,
        ::activation,
        CandidateCredentials()
    )

    @MockBean(SmtpMailerService::class)
    override fun smtpMailerService(): SmtpMailerService = super.smtpMailerService()
}
