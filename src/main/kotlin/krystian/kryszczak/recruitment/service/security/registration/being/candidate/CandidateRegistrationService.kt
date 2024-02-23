package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.service.security.registration.RegistrationService
import reactor.core.publisher.Mono

interface CandidateRegistrationService : RegistrationService<Candidate, CandidateFormation> {
    fun completeActivationUserAccount(email: String, code: String): Mono<Boolean>
}
