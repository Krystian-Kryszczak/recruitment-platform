package krystian.kryszczak.recruitment.model.security.code.reset

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.Code
import java.time.Instant

@Serdeable
@MappedEntity
data class ResetPassword(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val targetId: String,
    override val code: String,
    val accountType: AccountType,
    @DateCreated val dateCreated: Instant? = null
): Code(id, code) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResetPassword

        if (id != other.id) return false
        if (targetId != other.targetId) return false
        if (code != other.code) return false
        if (accountType != other.accountType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + targetId.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + accountType.hashCode()
        return result
    }
}
