package krystian.kryszczak.recruitment.repository.security.code.reset

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.reset.ResetPasswordRepository
import krystian.kryszczak.recruitment.model.constant.AccountType
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.repository.security.code.CodeRepositoryTest

@MicronautTest(transactional = false)
class ResetPasswordRepositoryTest(repository: ResetPasswordRepository) : CodeRepositoryTest<ResetPassword>(
repository,
arrayOf(
    ResetPassword(null, uniqueId(), "code-0", AccountType.CANDIDATE),
    ResetPassword(null, uniqueId(), "code-1", AccountType.EMPLOYER),
    ResetPassword(null, uniqueId(), "code-2", AccountType.CANDIDATE),
    ResetPassword(null, uniqueId(), "code-3", AccountType.EMPLOYER)
), { it.copy(code = "${it.code} - ${it.code}") },
{ item, id -> item.copy(id = id) })
