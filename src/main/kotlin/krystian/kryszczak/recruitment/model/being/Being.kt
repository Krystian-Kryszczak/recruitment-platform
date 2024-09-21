package krystian.kryszczak.recruitment.model.being

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.RestrictableItem

@Serdeable
abstract class Being<T : Being<T>>(id: String? = null, open val email: String? = null) : RestrictableItem<T>(id)
