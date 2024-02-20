package krystian.kryszczak.recruitment.service.account

import io.kotest.core.spec.style.FreeSpec
import krystian.kryszczak.recruitment.model.account.Account
import krystian.kryszczak.recruitment.service.DataAccessServiceTest

abstract class AccountServiceTest<T : Account>(
    accountService: AccountService<T>,
    givenItems: Array<T>,
    updateFun: (T) -> T,
    copyWithId: (item: T, id: String) -> T,
    body: FreeSpec.() -> Unit = {}
) : DataAccessServiceTest<T>(accountService, givenItems, updateFun, copyWithId, body)
