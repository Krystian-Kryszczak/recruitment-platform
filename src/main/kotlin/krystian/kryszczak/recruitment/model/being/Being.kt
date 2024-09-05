package krystian.kryszczak.recruitment.model.being

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item

@Serdeable
@Introspected
abstract class Being(id: String? = null) : Item(id)
