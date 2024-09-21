package krystian.kryszczak.recruitment.database.mongodb.repository.security.code.reset

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.CodeRepository
import krystian.kryszczak.recruitment.model.constant.AccountType
import reactor.core.publisher.Mono

@MongoRepository
interface ResetPasswordRepository : CodeRepository<ResetPassword> {
    fun findByAccountTypeAndCode(accountType: AccountType, code: String): Mono<ResetPassword>
}
