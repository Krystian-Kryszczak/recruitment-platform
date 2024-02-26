package krystian.kryszczak.recruitment.repository.security.code.reset

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.code.reset.ResetPassword
import krystian.kryszczak.recruitment.repository.security.code.CodeRepositoryTest

@MicronautTest
class ResetPasswordRepositoryTest(repository: ResetPasswordRepository) : CodeRepositoryTest<ResetPassword>(
repository,
arrayOf(
    ResetPassword(null, "code-0"),
    ResetPassword(null, "code-1"),
    ResetPassword(null, "code-2"),
    ResetPassword(null, "code-3")
),
{ it.copy(code = "${it.code} - ${it.code}") },
{ item, id -> item.copy(id = id) })
