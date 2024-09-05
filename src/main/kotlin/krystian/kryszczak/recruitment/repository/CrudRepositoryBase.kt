package krystian.kryszczak.recruitment.repository

import io.micronaut.data.repository.reactive.ReactorCrudRepository

interface CrudRepositoryBase<T> : ReactorCrudRepository<T, String>
