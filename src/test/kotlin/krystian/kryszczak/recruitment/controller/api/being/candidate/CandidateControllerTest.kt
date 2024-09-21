package krystian.kryszczak.recruitment.controller.api.being.candidate

import io.kotest.mpp.uniqueId
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.controller.api.being.BeingControllerTest
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.security.registration.being.candidate.CandidateRegistrationService

@MicronautTest(transactional = false)
class CandidateControllerTest(@Client("/api/v1/candidate") httpClient: HttpClient, tokenGenerator: JwtTokenGenerator) :
    BeingControllerTest<Candidate, CandidateCreationForm, CandidateUpdateForm, CandidateDto, CandidateCredentials, CandidateActivation>(httpClient, tokenGenerator,
        { CandidateUpdateForm("eliot@fsociety.com", "Eliot", "Alderson") },
        { CandidateCreationForm(
            "eliot@fsociety.com",
            "Eliot",
            "Alderson",
            "iammaster",
            agreeToEmailMarketing = true,
            password = "Hello friend!",
            acceptRules = true
        ) }, "CANDIDATE")
{
    @MockBean(CandidateService::class)
    fun beingService() = createBeingServiceMock<CandidateService, Candidate, CandidateUpdateForm>(
        Candidate(uniqueId(), "john@smith.com", "John", "Smith")
    )

    @MockBean(CandidateRegistrationService::class)
    fun candidateRegistrationService() = createBeingRegistrationServiceMock<CandidateRegistrationService, CandidateCreationForm>()
}
