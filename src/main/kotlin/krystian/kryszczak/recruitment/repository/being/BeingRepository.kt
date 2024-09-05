package krystian.kryszczak.recruitment.repository.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase

interface BeingRepository<T : Being> : CrudRepositoryBase<T>
