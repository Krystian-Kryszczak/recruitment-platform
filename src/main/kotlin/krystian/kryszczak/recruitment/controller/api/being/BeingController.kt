package krystian.kryszczak.recruitment.controller.api.being

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import jakarta.annotation.security.PermitAll
import jakarta.validation.Valid
import krystian.kryszczak.recruitment.extension.authentication.getClientId
import krystian.kryszczak.recruitment.model.Formation
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.Registration
import krystian.kryszczak.recruitment.service.being.BeingService
import reactor.core.publisher.Mono

abstract class BeingController<T : Being, S : Formation<T>>(private val service: BeingService<T, S>) {
    @Get
    fun getData(authentication: Authentication) =
        handleWithId(authentication) { id ->
            service.findById(id)
                .map { HttpResponse.ok(it) }
                .defaultIfEmpty(HttpResponse.notFound())
        }

    @PermitAll
    @Get("/{id}")
    fun findById(@PathVariable id: String): Mono<MutableHttpResponse<T>> =
        service.findById(id)
            .map { HttpResponse.ok(it) }
            .defaultIfEmpty(HttpResponse.notFound())

    @Delete(produces = [MediaType.APPLICATION_FORM_URLENCODED])
    fun remove(password: String, authentication: Authentication) =
        handleWithId(authentication) { id ->
            service.autoDeleteByUser(password, id)
                .map { if (it) HttpResponse.ok<Any>() else HttpResponse.unauthorized() }
                .defaultIfEmpty(HttpResponse.serverError())
        }

    @Put
    fun update(@Body formation: S, authentication: Authentication) =
        handleWithId(authentication) { id ->
            service.update(formation.format(id))
                .map { HttpResponse.ok(it) }
                .defaultIfEmpty(HttpResponse.serverError())
        }

    @PermitAll
    @Post("register", produces = [MediaType.APPLICATION_FORM_URLENCODED])
    fun register(@Valid @RequestBean bean: Registration): Mono<out HttpResponse<String>> {
        if (!bean.acceptRules) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE))
        return service.register(createFormation(bean), bean.password)
            .map { if (it) HttpResponse.ok<String>() else HttpResponse.status(HttpStatus.CONFLICT) }
        .defaultIfEmpty(HttpResponse.serverError())
    }

    @PermitAll
    @Post("activate", produces = [MediaType.APPLICATION_FORM_URLENCODED])
    fun activate(email: String, code: String): Mono<out HttpResponse<String>> =
        service.completeActivation(email, code)
            .map { if (it) HttpResponse.ok<String>() else HttpResponse.unauthorized() }
            .defaultIfEmpty(HttpResponse.serverError())

    private inline fun <T> handleWithId(authentication: Authentication,
            crossinline body: (id: String) -> Mono<out HttpResponse<T>>): Mono<out HttpResponse<T>> {
        val clientId = authentication.getClientId() ?: return Mono.just(HttpResponse.unauthorized())
        return body(clientId)
    }

    protected abstract fun createFormation(registration: Registration): S
}
