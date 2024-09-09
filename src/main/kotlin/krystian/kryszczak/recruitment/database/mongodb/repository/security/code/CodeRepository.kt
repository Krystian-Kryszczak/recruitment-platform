package krystian.kryszczak.recruitment.database.mongodb.repository.security.code

import krystian.kryszczak.recruitment.model.security.code.Code
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase

interface CodeRepository<T : Code> : CrudRepositoryBase<T, String>
