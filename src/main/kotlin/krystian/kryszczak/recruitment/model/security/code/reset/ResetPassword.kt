package krystian.kryszczak.recruitment.model.security.code.reset

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.security.code.Code
import krystian.kryszczak.recruitment.security.generator.reset.ResetPasswordCodeGenerator
import java.time.Instant

@Serdeable
@MappedEntity
@Introspected
data class ResetPassword(
    @field:Id @field:GeneratedValue override val id: String? = null,
    val targetId: String,
    override val code: String,
    val type: String,
    @DateCreated val dateCreated: Instant? = null
): Code(id, code) {
    companion object {
        fun createWithGeneratedCode(targetId: String, type: String, generator: ResetPasswordCodeGenerator) =
            ResetPassword(null, targetId, generator.generateCode(), type)
    }
}
