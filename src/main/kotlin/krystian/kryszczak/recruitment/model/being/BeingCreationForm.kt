package krystian.kryszczak.recruitment.model.being

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.CreationForm

@Serdeable
abstract class BeingCreationForm<T : Being<T>, S : BeingCreationForm<T, S>>(
    open val email: String,
    open val password: String,
    open val acceptRules: Boolean
) : CreationForm<T, S>
