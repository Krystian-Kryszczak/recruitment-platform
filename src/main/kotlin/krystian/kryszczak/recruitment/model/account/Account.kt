package krystian.kryszczak.recruitment.model.account

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item

@Serdeable
@Introspected
abstract class Account(
    override val id: String? = null
) : Item(id)
