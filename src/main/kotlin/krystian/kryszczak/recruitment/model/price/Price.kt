package krystian.kryszczak.recruitment.model.price

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Price(val amount: Double, val currency: String)
