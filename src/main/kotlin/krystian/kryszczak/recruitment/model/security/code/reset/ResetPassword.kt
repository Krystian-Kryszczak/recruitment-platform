package krystian.kryszczak.recruitment.model.security.code.reset

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.security.code.Code
import krystian.kryszczak.recruitment.security.generator.reset.ResetPasswordCodeGeneratorImpl

@Serdeable
@MappedEntity
@Introspected
data class ResetPassword(
    @field:Id @field:GeneratedValue override val id: String? = null,
    override val code: String
): Code(id, code) {
    companion object {
        fun createWithGeneratedCode(userId: String) = ResetPassword(ResetPasswordCodeGeneratorImpl.generateCode(), userId)
    }
}
