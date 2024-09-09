package krystian.kryszczak.recruitment.model.security.code.activation.being

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.CreationForm
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.security.code.activation.Activation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials

@Serdeable
abstract class BeingActivation<T : Being, S : CreationForm<T, S>, U : BeingCredentials>(
    override val id: String? = null,
    code: String,
    open val identity: String,
    open val creationForm: S?,
    open val encodedPassword: String
): Activation(id, code)
