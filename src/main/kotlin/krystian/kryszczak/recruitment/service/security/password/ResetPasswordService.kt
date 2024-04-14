package krystian.kryszczak.recruitment.service.security.password

import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import reactor.core.publisher.Mono
import java.util.UUID

interface ResetPasswordService {
    fun generateChangeUserPasswordCode(id: UUID, oldPassword: String): Mono<ResetPassword>
    fun saveResetPassword(resetPassword: ResetPassword): Mono<Boolean>
    fun sendChangeUserPasswordCodeToEmail(resetPassword: ResetPassword, email: String): Mono<Boolean>
    fun changeUserPassword(id: UUID, resetPasswordCode: String, newPassword: String): Mono<Boolean>
}
