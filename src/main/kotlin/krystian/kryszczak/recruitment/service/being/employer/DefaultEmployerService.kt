package krystian.kryszczak.recruitment.service.being.employer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.employer.EmployerActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.database.mongodb.repository.being.employer.EmployerRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.employer.EmployerCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.employer.EmployerMapper
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.service.being.BeingServiceBase
import reactor.core.publisher.Mono

@Singleton
class DefaultEmployerService(
    override val repository: EmployerRepository,
    passwordEncoder: PasswordEncoder,
    credentialsRepository: EmployerCredentialsRepository,
    mapper: EmployerMapper
) : BeingServiceBase<Employer, EmployerDto, EmployerCreationForm, EmployerUpdateForm, EmployerCredentials, EmployerActivation>(
    repository, passwordEncoder, credentialsRepository, mapper
), EmployerService {
    override fun getEmployerName(id: String): Mono<String> = repository.findNameById(id)
}
