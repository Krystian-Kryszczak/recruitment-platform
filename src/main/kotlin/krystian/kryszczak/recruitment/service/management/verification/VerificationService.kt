package krystian.kryszczak.recruitment.service.management.verification

import reactor.core.publisher.Mono

interface VerificationService<T, ID> {
    fun verifyById(id: ID): Mono<T>
    fun unverifyById(id: ID): Mono<T>
    fun isVerifiedById(id: ID): Mono<Boolean>
}
