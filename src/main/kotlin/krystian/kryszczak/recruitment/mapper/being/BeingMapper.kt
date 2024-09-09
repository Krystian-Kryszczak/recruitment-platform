package krystian.kryszczak.recruitment.mapper.being

import krystian.kryszczak.recruitment.mapper.ItemMapper
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import reactor.core.publisher.Mono

interface BeingMapper<T : Being, S : BeingDto<T, S>, V : BeingCreationForm<T, V>, U : BeingUpdateForm<T, U>,
        C : BeingCredentials, A : BeingActivation<T, V, C>> : ItemMapper<T, S, V, U, String> {
    fun mapToOriginItem(activation: A): Mono<T>
    fun mapToCredentials(activation: A): C
}
