package krystian.kryszczak.recruitment.database.mongodb.repository.security.code.reset

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.CodeRepository

@MongoRepository
interface ResetPasswordRepository : CodeRepository<ResetPassword>
