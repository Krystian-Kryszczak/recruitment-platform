package krystian.kryszczak.recruitment.factory.password

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.security.generator.reset.ResetPasswordCodeGenerator

@Singleton
class DefaultResetPasswordFactory(private val generator: ResetPasswordCodeGenerator) : ResetPasswordFactory {
    override fun createWithGeneratedCode(targetId: String, accountType: AccountType): ResetPassword =
        ResetPassword(null, targetId, generator.generateCode(), accountType)
}
