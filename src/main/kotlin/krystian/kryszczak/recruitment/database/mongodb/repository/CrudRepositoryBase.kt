package krystian.kryszczak.recruitment.database.mongodb.repository

import io.micronaut.data.repository.reactive.ReactorCrudRepository

interface CrudRepositoryBase<T, ID> : ReactorCrudRepository<T, ID>
