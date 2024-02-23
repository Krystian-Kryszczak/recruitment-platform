package krystian.kryszczak.recruitment.model.security.code.activation.being

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.security.code.activation.Activation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import java.beans.Transient

@Serdeable
@Introspected
abstract class BeingActivation<T : Being, S : Formation<T>, U : BeingCredentials>(
    override val id: String? = null,
    override val code: String,
    open val identity: String,
    open val formation: S?,
    open val encodedPassword: String
): Item(id), Activation {
    @Transient
    abstract fun toFormationTarget(): T?

    @Transient
    abstract fun toCredentials(): U?
}
