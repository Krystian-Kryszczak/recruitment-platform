package krystian.kryszczak.recruitment.controller.api.being.employer

import io.kotest.mpp.uniqueId
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.controller.api.being.BeingControllerTest
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.service.being.employer.EmployerService

@MicronautTest(transactional = false)
class EmployerControllerTest(@Client("/api/v1/employer") httpClient: HttpClient, tokenGenerator: JwtTokenGenerator) :
    BeingControllerTest<Employer, EmployerCreationForm, EmployerUpdateForm>(httpClient, tokenGenerator,
        { EmployerUpdateForm("recruitment@e-corp.com", "E-Corp") },
        { EmployerCreationForm(
            "eliot@fsociety.com",
            "Eliot",
            "Alderson",
            "iammaster",
            agreeToEmailMarketing = true,
            password = "Hello friend!",
            acceptRules = true
        ) }, "EMPLOYER") {
    @MockBean(EmployerService::class)
    fun mockService() = createServiceMock<EmployerService, Employer, EmployerCreationForm>(
        Employer(uniqueId(), "test")
    )
}
