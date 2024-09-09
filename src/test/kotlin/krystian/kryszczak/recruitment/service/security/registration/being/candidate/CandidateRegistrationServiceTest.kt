package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.service.security.registration.RegistrationServiceTest

@MicronautTest(transactional = false)
class CandidateRegistrationServiceTest(registrationService: CandidateRegistrationService) :
    RegistrationServiceTest<Candidate, CandidateCreationForm>(registrationService)
