package krystian.kryszczak.recruitment.repository.security.code

import krystian.kryszczak.recruitment.model.security.code.Code
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase

interface CodeRepository<T : Code> : CrudRepositoryBase<T>
