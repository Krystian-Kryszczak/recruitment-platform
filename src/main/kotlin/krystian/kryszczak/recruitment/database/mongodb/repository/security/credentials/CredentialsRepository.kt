package krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials

import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase

interface CredentialsRepository<T : Credentials> : CrudRepositoryBase<T, String>
