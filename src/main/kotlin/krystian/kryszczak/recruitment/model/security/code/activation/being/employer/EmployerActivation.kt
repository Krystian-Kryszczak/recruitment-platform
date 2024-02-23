package krystian.kryszczak.recruitment.model.security.code.activation.being.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.formation.EmployerFormation
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.employer.EmployerCredentials
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import java.beans.Transient

@Serdeable
@MappedEntity
@Introspected
class EmployerActivation(
    override val id: String? = null,
    override val code: String,
    override val identity: String,
    override val formation: EmployerFormation,
    override val encodedPassword: String
) : BeingActivation<Employer, EmployerFormation, EmployerCredentials>(id, code, identity, formation, encodedPassword) {
    @Transient
    override fun toFormationTarget() = formation.format(id)

    @Transient
    override fun toCredentials() = EmployerCredentials(null, identity, encodedPassword)

    companion object {
        fun createWithGeneratedCode(identity: String, formation: EmployerFormation, encodedPassword: String) =
            EmployerActivation(null, ActivationCodeGenerator.generateCode(), identity, formation, encodedPassword)
    }
}
