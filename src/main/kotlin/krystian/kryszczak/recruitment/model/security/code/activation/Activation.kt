package krystian.kryszczak.recruitment.model.security.code.activation

import krystian.kryszczak.recruitment.model.security.code.Code

abstract class Activation(override val id: String? = null, override val code: String) : Code(id, code)
