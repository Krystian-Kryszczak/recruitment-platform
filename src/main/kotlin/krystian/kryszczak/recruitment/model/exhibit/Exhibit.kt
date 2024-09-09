package krystian.kryszczak.recruitment.model.exhibit

import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item

@Serdeable
abstract class Exhibit(id: String?) : Item(id)
