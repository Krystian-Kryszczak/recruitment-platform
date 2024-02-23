package krystian.kryszczak.recruitment.repository.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.repository.BaseCrudRepository

interface BeingRepository<T : Being> : BaseCrudRepository<T>
