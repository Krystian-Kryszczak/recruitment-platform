package krystian.kryszczak.recruitment.model.security.credentials.being

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import java.beans.Transient

@Serdeable
abstract class BeingCredentials(
    override val id: String? = null,
    open val username: String? = null,
    open val hashedPassword: String? = null
): Credentials(id) {
    @Transient
    override fun getIdentity(): String = username!!

    @Transient
    override fun getSecret(): String = hashedPassword!!
}
