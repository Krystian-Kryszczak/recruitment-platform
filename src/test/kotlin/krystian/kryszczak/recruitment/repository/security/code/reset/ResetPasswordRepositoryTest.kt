package krystian.kryszczak.recruitment.repository.security.code.reset

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.repository.security.code.CodeRepositoryTest

@MicronautTest
class ResetPasswordRepositoryTest(repository: ResetPasswordRepository) : CodeRepositoryTest<ResetPassword>(
repository,
arrayOf(
    ResetPassword(null, uniqueId(), "code-0", "CANDIDATE"),
    ResetPassword(null, uniqueId(), "code-1", "EMPLOYER"),
    ResetPassword(null, uniqueId(), "code-2", "CANDIDATE"),
    ResetPassword(null, uniqueId(), "code-3", "EMPLOYER")
), { it.copy(code = "${it.code} - ${it.code}") },
{ item, id -> item.copy(id = id) })
