package krystian.kryszczak.recruitment.repository

import io.micronaut.data.repository.reactive.ReactorCrudRepository

interface BaseCrudRepository<T> : ReactorCrudRepository<T, String>
