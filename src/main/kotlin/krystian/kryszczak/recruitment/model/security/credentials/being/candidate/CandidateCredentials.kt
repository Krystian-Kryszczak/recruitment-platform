package krystian.kryszczak.recruitment.model.security.credentials.being.candidate

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import java.beans.Transient

@Serdeable
@MappedEntity
@Introspected
data class CandidateCredentials(
    @field:Id @field:GeneratedValue override val id: String? = null,
    override val username: String? = null,
    override val hashedPassword: String? = null
): BeingCredentials(id, username, hashedPassword) {
    @Transient
    override fun getIdentity(): String = username!!

    @Transient
    override fun getSecret(): String = hashedPassword!!
}
