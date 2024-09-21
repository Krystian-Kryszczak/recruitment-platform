package krystian.kryszczak.recruitment.model.security.code.activation.being.employer

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials

@Serdeable
@MappedEntity
data class EmployerActivation(
    @field:Id @field:GeneratedValue override val id: String? = null,
    override val code: String,
    override val identity: String,
    override val creationForm: EmployerCreationForm,
    override val encodedPassword: String
) : BeingActivation<Employer, EmployerCreationForm, EmployerCredentials>(id, code, identity, creationForm, encodedPassword)
