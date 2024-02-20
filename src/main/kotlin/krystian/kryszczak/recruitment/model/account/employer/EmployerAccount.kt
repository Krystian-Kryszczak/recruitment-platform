package krystian.kryszczak.recruitment.model.account.employer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.account.Account

@Serdeable
@MappedEntity
@Introspected
data class EmployerAccount(@field:Id @field:GeneratedValue override val id: String? = null): Account(id)
