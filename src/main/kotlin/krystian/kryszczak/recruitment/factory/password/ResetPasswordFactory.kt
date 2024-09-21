package krystian.kryszczak.recruitment.factory.password

import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword

interface ResetPasswordFactory {
    fun createWithGeneratedCode(targetId: String, accountType: AccountType): ResetPassword
}
