package krystian.kryszczak.recruitment.service.being.employer

import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.service.being.BeingService
import reactor.core.publisher.Mono

interface EmployerService : BeingService<Employer, EmployerCreationForm, EmployerUpdateForm> {
    fun getEmployerName(id: String): Mono<String>
}
