package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Named
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.service.security.registration.RegistrationService
import krystian.kryszczak.recruitment.service.security.registration.RegistrationServiceTest

@MicronautTest(transactional = false)
class CandidateRegistrationServiceTest(@Named("candidate") registrationService: RegistrationService<Candidate, CandidateCreationForm>) :
    RegistrationServiceTest<Candidate, CandidateCreationForm>(registrationService)
