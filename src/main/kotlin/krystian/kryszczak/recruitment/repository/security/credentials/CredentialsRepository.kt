package krystian.kryszczak.recruitment.repository.security.credentials

import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import krystian.kryszczak.recruitment.repository.BaseCrudRepository

interface CredentialsRepository<T : Credentials> : BaseCrudRepository<T>
