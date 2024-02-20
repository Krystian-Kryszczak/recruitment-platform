package krystian.kryszczak.recruitment.service.account

import krystian.kryszczak.recruitment.model.account.Account
import krystian.kryszczak.recruitment.service.DataAccessService

interface AccountService<T : Account> : DataAccessService<T, String>
