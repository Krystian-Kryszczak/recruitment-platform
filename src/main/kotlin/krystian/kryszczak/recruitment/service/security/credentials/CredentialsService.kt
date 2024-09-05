package krystian.kryszczak.recruitment.service.security.credentials

import krystian.kryszczak.recruitment.model.security.credentials.Credentials
import krystian.kryszczak.recruitment.service.DataAccessService

interface CredentialsService<T : Credentials> : DataAccessService<T, String>
