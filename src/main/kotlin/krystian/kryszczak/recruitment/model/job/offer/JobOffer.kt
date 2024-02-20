package krystian.kryszczak.recruitment.model.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.Item

@Serdeable
@MappedEntity
@Introspected
data class JobOffer(
    @field:Id @field:GeneratedValue override val id: String? = null
): Item(id)
