package krystian.kryszczak.recruitment.model.being

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item

@Serdeable
abstract class Being(id: String? = null) : Item(id)
