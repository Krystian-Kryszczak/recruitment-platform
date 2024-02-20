package krystian.kryszczak.recruitment.repository.account

import io.micronaut.data.repository.reactive.ReactorCrudRepository
import krystian.kryszczak.recruitment.model.account.Account

interface AccountRepository<T : Account> : ReactorCrudRepository<T, String>
