package krystian.kryszczak.recruitment.database.mongodb.repository.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase

interface BeingRepository<T : Being> : CrudRepositoryBase<T, String>
