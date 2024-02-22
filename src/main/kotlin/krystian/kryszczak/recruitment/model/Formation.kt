package krystian.kryszczak.recruitment.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
abstract class Formation<T : Item> {
    abstract fun format(id: String? = null): T
}
