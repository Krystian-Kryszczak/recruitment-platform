package krystian.kryszczak.recruitment.repository.security.code.activation.being

import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.repository.security.code.activation.ActivationRepository

interface BeingActivationRepository<T : BeingActivation<*, *, *>> : ActivationRepository<T>
