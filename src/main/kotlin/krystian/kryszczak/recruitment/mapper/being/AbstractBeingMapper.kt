package krystian.kryszczak.recruitment.mapper.being

import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.security.generator.activation.ActivationCodeGenerator
import org.bson.types.ObjectId
import reactor.core.publisher.Mono

abstract class AbstractBeingMapper<T : Being<T>, S : BeingDto<T, S>, V : BeingCreationForm<T, V>, U : BeingUpdateForm<T, U>, C : BeingCredentials, A : BeingActivation<T, V, C>>(
    private val passwordEncoder: PasswordEncoder,
    private val codeGenerator: ActivationCodeGenerator
) : BeingMapper<T, S, V, U, C, A> {
    protected fun mapToRegisterActivation(
        form: V,
        map: (id: String?, code: String, identity: String, creationForm: V, encodedPassword: String) -> A
    ): Mono<A> = with(form) {
        Mono.just(
            map(null, codeGenerator.generateCode(), email, form, passwordEncoder.encode(password))
        )
    }

    override fun mapToOriginItem(activation: A): Mono<T> = with(activation) {
        mapToOriginItem(creationForm, id ?: ObjectId.get().toHexString())
    }

    protected fun mapToCredentials(activation: A, map: (id: String?, username: String?, hashedPassword: String?) -> C): C = with(activation) {
        map(null, identity, encodedPassword)
    }
}
