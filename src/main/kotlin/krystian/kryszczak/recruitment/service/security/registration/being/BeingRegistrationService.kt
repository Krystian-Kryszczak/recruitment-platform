package krystian.kryszczak.recruitment.service.security.registration.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.service.security.registration.RegistrationService

interface BeingRegistrationService<T : Being<T>, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>,
    D : BeingDto<T, D>, C : BeingCredentials, A : BeingActivation<T, S, C>> : RegistrationService<T, S>
