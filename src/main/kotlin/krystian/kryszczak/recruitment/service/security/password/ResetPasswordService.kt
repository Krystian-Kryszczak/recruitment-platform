package krystian.kryszczak.recruitment.service.security.password

import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.service.DataAccessService
import reactor.core.publisher.Mono

interface ResetPasswordService : DataAccessService<ResetPassword, String> {
    fun generateResetPasswordCode(accountType: AccountType, id: String, oldPassword: String): Mono<ResetPassword>
    fun sendResetPasswordCodeToEmail(resetPassword: ResetPassword, email: String): Mono<Boolean>
    fun finishResetPasswordProcess(accountType: AccountType, resetCode: String, newPassword: String): Mono<Boolean>
}
