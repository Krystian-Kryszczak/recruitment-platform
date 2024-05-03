package krystian.kryszczak.recruitment.controller.api.being.employer

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.being.BeingController
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto.Companion as mapper
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.service.being.employer.EmployerService

@RolesAllowed("EMPLOYER")
@Controller("api/v1/employer/")
@ExecuteOn(TaskExecutors.BLOCKING)
open class EmployerController(service: EmployerService) :
    BeingController<Employer, EmployerCreationForm, EmployerUpdateForm, EmployerDto>(service, mapper)
