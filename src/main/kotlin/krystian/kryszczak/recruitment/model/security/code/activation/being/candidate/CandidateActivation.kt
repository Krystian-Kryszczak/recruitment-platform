package krystian.kryszczak.recruitment.model.security.code.activation.being.candidate

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials

@Serdeable
@MappedEntity
data class CandidateActivation(
    @field:Id @field:GeneratedValue override val id: String? = null,
    override val code: String,
    override val identity: String,
    override val creationForm: CandidateCreationForm,
    override val encodedPassword: String
) : BeingActivation<Candidate, CandidateCreationForm, CandidateCredentials>(id, code, identity, creationForm, encodedPassword)
