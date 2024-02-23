package krystian.kryszczak.recruitment.service

import krystian.kryszczak.recruitment.model.Item
import krystian.kryszczak.recruitment.repository.BaseCrudRepository

abstract class DataAccessServiceImpl<T : Item>(repository: BaseCrudRepository<T>) :
    DataAccessServiceBase<T, String>(repository)
