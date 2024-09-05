package krystian.kryszczak.recruitment.repository.security.code.activation

import krystian.kryszczak.recruitment.model.security.code.activation.Activation
import krystian.kryszczak.recruitment.repository.security.code.CodeRepository

interface ActivationRepository<T : Activation> : CodeRepository<T>
