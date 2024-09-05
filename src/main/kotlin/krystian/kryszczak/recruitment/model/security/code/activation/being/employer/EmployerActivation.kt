package krystian.kryszczak.recruitment.model.security.code.activation.being.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import java.beans.Transient

@Serdeable
@MappedEntity
@Introspected
data class EmployerActivation(
    @field:Id @field:GeneratedValue override val id: String? = null,
    override val code: String,
    override val identity: String,
    override val creationForm: EmployerCreationForm,
    override val encodedPassword: String
) : BeingActivation<Employer, EmployerCreationForm, EmployerCredentials>(id, code, identity, creationForm, encodedPassword) {
    @Transient
    override fun toFormationTarget() = creationForm.transform()

    @Transient
    override fun toCredentials() = EmployerCredentials(null, identity, encodedPassword)

    companion object {
        fun createWithGeneratedCode(identity: String, formation: EmployerCreationForm, encodedPassword: String, generator: ActivationCodeGenerator) =
            EmployerActivation(null, generator.generateCode(), identity, formation, encodedPassword)
    }
}
