package krystian.kryszczak.recruitment.model.job

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item

@Serdeable
@Introspected
abstract class Exhibit(id: String?, banned: Boolean = false) : Item(id)
