package krystian.kryszczak.recruitment.controller.api.being.candidate

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.being.BeingController
import krystian.kryszczak.recruitment.model.being.Registration
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService

@RolesAllowed("CANDIDATE")
@Controller("api/v1/candidate")
@ExecuteOn(TaskExecutors.BLOCKING)
class CandidateController(service: CandidateService): BeingController<Candidate, CandidateFormation>(service) {
    override fun createFormation(registration: Registration) =
        CandidateFormation(registration.email, registration.name, registration.lastName,
            agreeToEmailMarketing = registration.agreeToEmailMarketing)
}
