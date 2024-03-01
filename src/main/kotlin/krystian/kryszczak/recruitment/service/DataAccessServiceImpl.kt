package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase

abstract class DataAccessServiceImpl<T : Item>(repository: CrudRepositoryBase<T>) :
    DataAccessServiceBase<T, String>(repository)
