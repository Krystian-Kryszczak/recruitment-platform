package krystian.kryszczak.recruitment.controller.api.being.employer

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.being.BeingController
import krystian.kryszczak.recruitment.mapper.being.employer.EmployerMapper
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.security.registration.being.employer.EmployerRegistrationService

@RolesAllowed("EMPLOYER")
@Controller("api/v1/employer/")
@ExecuteOn(TaskExecutors.IO)
open class EmployerController(service: EmployerService, registrationService: EmployerRegistrationService, mapper: EmployerMapper) :
    BeingController<Employer, EmployerCreationForm, EmployerUpdateForm, EmployerDto, EmployerCredentials, EmployerActivation>(service, registrationService, mapper)
