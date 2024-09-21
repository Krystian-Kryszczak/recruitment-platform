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
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.security.registration.being.employer.EmployerRegistrationService

@MicronautTest(transactional = false)
class EmployerControllerTest(@Client("/api/v1/employer") httpClient: HttpClient, tokenGenerator: JwtTokenGenerator) :
    BeingControllerTest<Employer, EmployerCreationForm, EmployerUpdateForm, EmployerDto, EmployerCredentials, EmployerActivation>(
        httpClient, tokenGenerator,
        { EmployerUpdateForm("recruitment@e-corp.com", "E-Corp") },
        { EmployerCreationForm(
            "eliot@fsociety.com",
            "Eliot",
            "Alderson",
            "iammaster",
            agreeToEmailMarketing = true,
            password = "Hello friend!",
            country = "USA",
            acceptRules = true
        ) }, "EMPLOYER")
{
    @MockBean(EmployerService::class)
    fun beingService() = createBeingServiceMock<EmployerService, Employer, EmployerUpdateForm>(
        Employer(uniqueId(), "Eliot", country = "USA")
    )

    @MockBean(EmployerRegistrationService::class)
    fun employerRegistrationService() = createBeingRegistrationServiceMock<EmployerRegistrationService, EmployerCreationForm>()
}
