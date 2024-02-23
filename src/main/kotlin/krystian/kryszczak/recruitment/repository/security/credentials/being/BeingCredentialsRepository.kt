package krystian.kryszczak.recruitment.repository.security.credentials.being

import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.repository.security.credentials.CredentialsRepository

interface BeingCredentialsRepository<T : BeingCredentials> : CredentialsRepository<T>
