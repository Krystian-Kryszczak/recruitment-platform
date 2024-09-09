package krystian.kryszczak.recruitment.model

import io.micronaut.serde.annotation.Serdeable

@Serdeable
abstract class Item(open val id: String? = null)
