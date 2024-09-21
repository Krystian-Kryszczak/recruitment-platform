package krystian.kryszczak.recruitment.controller.api.moderation.verification.employer

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.token.generator.TokenGenerator
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.controller.api.moderation.verification.VerificationControllerTest
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.service.management.verification.employer.EmployerVerificationService
import krystian.kryszczak.test.mock.employerMock

@MicronautTest(transactional = false)
class EmployerVerificationControllerTest(
    @Client("/api/v1/moderation/verification/") httpClient: HttpClient,
    generator: TokenGenerator
) : VerificationControllerTest(httpClient, generator) {
    @MockBean(EmployerVerificationService::class)
    fun employerVerificationService() = verificationService<Employer, String, EmployerVerificationService>(employerMock)
}
