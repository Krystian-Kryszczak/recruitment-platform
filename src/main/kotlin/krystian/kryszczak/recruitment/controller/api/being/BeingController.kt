package krystian.kryszczak.recruitment.controller.api.being

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import jakarta.annotation.security.PermitAll
import jakarta.validation.Valid
import krystian.kryszczak.recruitment.controller.api.ID_PATTERN
import krystian.kryszczak.recruitment.extension.authentication.getClientId
import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.service.being.BeingService
import reactor.core.publisher.Mono

abstract class BeingController<T : Being, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>, V : BeingDto<T, V>, C : BeingCredentials, A : BeingActivation<T, S, C>>(
    private val service: BeingService<T, S, U>,
    private val dtoMapper: BeingMapper<T, V, S, U, C, A>
) {
    @PermitAll
    @Get("{/id:$ID_PATTERN}")
    fun findById(id: String?, authentication: Authentication?) = handleWithIdPermitAll(authentication) { clientId ->
        (id ?: clientId)?.let { id ->
            service.findById(id)
                .map { HttpResponse.ok(dtoMapper.mapToDto(it)) }
                .defaultIfEmpty(HttpResponse.notFound())
        } ?: Mono.just(HttpResponse.status(HttpStatus.NO_CONTENT))
    }

    @Put(consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    open fun update(@Valid @RequestBean bean: U, authentication: Authentication) = handleWithId(authentication) { id ->
        service.update(id, bean)
            .map { HttpResponse.ok(dtoMapper.mapToDto(it)) }
            .defaultIfEmpty(HttpResponse.serverError())
    }

    @Delete(consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    fun remove(password: String, authentication: Authentication) = handleWithId(authentication) { id ->
        service.autoDeleteByUser(password, id)
            .map { if (it) HttpResponse.ok<Any>() else HttpResponse.unauthorized() }
            .defaultIfEmpty(HttpResponse.serverError())
    }

    @PermitAll
    @Post("/register", consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    open fun register(@Valid @RequestBean bean: S): Mono<out HttpResponse<String>> {
        if (!bean.acceptRules) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE))
        return service.register(bean, bean.password)
            .map { if (it) HttpResponse.ok<String>() else HttpResponse.status(HttpStatus.CONFLICT) }
        .defaultIfEmpty(HttpResponse.serverError())
    }

    @PermitAll
    @Post("/activate", consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    fun activate(email: String, code: String): Mono<out HttpResponse<String>> =
        service.completeActivation(email, code)
            .map { if (it) HttpResponse.ok<String>() else HttpResponse.unauthorized() }
            .defaultIfEmpty(HttpResponse.serverError())

    private inline fun <T> handleWithId(
        authentication: Authentication,
        crossinline body: (id: String) -> Mono<out HttpResponse<T>>
    ): Mono<out HttpResponse<T>> {
        val clientId = authentication.getClientId()
            ?: return Mono.just(HttpResponse.status(HttpStatus.FAILED_DEPENDENCY, "missing client id"))
        return body(clientId)
    }

    private inline fun <T> handleWithIdPermitAll(
        authentication: Authentication?,
        crossinline body: (id: String?) -> Mono<out HttpResponse<T>>
    ) = body(authentication?.getClientId())
}
