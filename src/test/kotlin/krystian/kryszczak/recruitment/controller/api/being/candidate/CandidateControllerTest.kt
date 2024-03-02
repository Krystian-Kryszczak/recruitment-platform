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
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService

@MicronautTest(transactional = false)
class CandidateControllerTest(@Client("/api/v1/candidate") httpClient: HttpClient, tokenGenerator: JwtTokenGenerator) :
    BeingControllerTest<Candidate, CandidateCreationForm, CandidateUpdateForm>(httpClient, tokenGenerator,
        { CandidateUpdateForm("eliot@fsociety.com", "Eliot", "Alderson") },
        { CandidateCreationForm(
            "eliot@fsociety.com",
            "Eliot",
            "Alderson",
            "iammaster",
            agreeToEmailMarketing = true,
            password = "Hello friend!",
            acceptRules = true
        ) }, "CANDIDATE") {
    @MockBean(CandidateService::class)
    fun mockService() = createServiceMock<CandidateService, Candidate, CandidateCreationForm>(
        Candidate(uniqueId(), "john@smith.com", "John", "Smith")
    )
}
