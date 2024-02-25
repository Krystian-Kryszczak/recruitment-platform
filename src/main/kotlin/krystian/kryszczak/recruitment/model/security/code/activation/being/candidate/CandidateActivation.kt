package krystian.kryszczak.recruitment.model.security.code.activation.being.candidate

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import java.beans.Transient

@Serdeable
@MappedEntity
@Introspected
data class CandidateActivation(
    @field:Id @field:GeneratedValue override val id: String? = null,
    override val code: String,
    override val identity: String,
    override val formation: CandidateFormation,
    override val encodedPassword: String
) : BeingActivation<Candidate, CandidateFormation, CandidateCredentials>(id, code, identity, formation, encodedPassword) {
    @Transient
    override fun toFormationTarget() = formation.format(id)

    @Transient
    override fun toCredentials() = CandidateCredentials(null, identity, encodedPassword)

    companion object {
        fun createWithGeneratedCode(identity: String, formation: CandidateFormation, encodedPassword: String) =
            CandidateActivation(null, ActivationCodeGenerator.generateCode(), identity, formation, encodedPassword)
    }
}
