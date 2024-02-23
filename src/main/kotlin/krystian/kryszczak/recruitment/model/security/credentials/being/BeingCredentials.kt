package krystian.kryszczak.recruitment.model.security.credentials.being

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import java.beans.Transient

@Serdeable
@Introspected
abstract class BeingCredentials(
    override val id: String? = null,
    open val username: String? = null,
    open val hashedPassword: String? = null
): Credentials, Item(id) {
    @Transient
    override fun getIdentity(): String = username!!

    @Transient
    override fun getSecret(): String = hashedPassword!!
}
