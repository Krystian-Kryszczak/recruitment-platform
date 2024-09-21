package krystian.kryszczak.recruitment.controller.api.moderation.verification

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.service.management.verification.VerificationService
import reactor.core.publisher.Mono

@RolesAllowed("ADMIN")
abstract class VerificationController<T, ID>(protected val verificationService: VerificationService<T, ID>) {
    @Post("/verify/employer/{id}")
    fun verify(id: ID) = verificationService.verifyById(id).hasElement().map()

    @Post("/unverify/employer/{id}")
    fun unverify(id: ID) = verificationService.unverifyById(id).hasElement().map()

    @Get("/is-verified/employer/{id}")
    fun isVerified(id: ID) = verificationService.isVerifiedById(id)

    private fun Mono<Boolean>.map() = filter { it }
        .thenReturn(HttpResponse.accepted<String>())
        .defaultIfEmpty(HttpResponse.notModified())
}
