package krystian.kryszczak.recruitment.model.security.code.activation

import krystian.kryszczak.recruitment.model.security.code.Code

abstract class Activation(id: String? = null, code: String) : Code(id, code)
