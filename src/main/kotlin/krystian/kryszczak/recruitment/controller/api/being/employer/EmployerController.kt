package krystian.kryszczak.recruitment.controller.api.being.employer

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.being.BeingController
import krystian.kryszczak.recruitment.model.being.Registration
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import krystian.kryszczak.recruitment.service.being.employer.EmployerService

@RolesAllowed("EMPLOYER")
@Controller("api/v1/employer")
@ExecuteOn(TaskExecutors.BLOCKING)
class EmployerController(service: EmployerService): BeingController<Employer, EmployerFormation>(service) {
    override fun createFormation(registration: Registration) =
        EmployerFormation(registration.email, registration.name,
            agreeToEmailMarketing = registration.agreeToEmailMarketing)
}
