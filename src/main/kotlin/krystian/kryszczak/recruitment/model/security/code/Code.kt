package krystian.kryszczak.recruitment.model.security.code

import krystian.kryszczak.recruitment.model.Item

abstract class Code(override val id: String? = null, open val code: String) : Item(id)
