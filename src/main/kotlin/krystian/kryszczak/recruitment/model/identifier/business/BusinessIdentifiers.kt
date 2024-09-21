package krystian.kryszczak.recruitment.model.identifier.business

import io.micronaut.serde.annotation.Serdeable

@Serdeable
class BusinessIdentifiers(
    val country: String,
    val taxId: String,
    val nationalBusinessRegistryNumber: String? = null,
    val nationalCourtRegisterNumber: String? = null
) {
    override fun toString(): String {
        return "$country $taxId $nationalBusinessRegistryNumber $nationalCourtRegisterNumber"
    }
}
