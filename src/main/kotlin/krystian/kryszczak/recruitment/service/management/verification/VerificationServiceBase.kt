package krystian.kryszczak.recruitment.service.management.verification

import krystian.kryszczak.recruitment.model.RestrictableItem
import krystian.kryszczak.recruitment.service.RestrictableDataAccessService
import reactor.core.publisher.Mono

abstract class VerificationServiceBase<T : RestrictableItem<T>>(
    private val dataAccessService: RestrictableDataAccessService<T, *, String>,
    private val copy: T.(verified: Boolean) -> T,
    private val isVerified: T.() -> Boolean
): VerificationService<T, String> {
    override fun verifyById(id: String): Mono<T> =
        dataAccessService.findById(id)
            .map { copy(it, true) }
            .flatMap(dataAccessService::update)

    override fun unverifyById(id: String): Mono<T> =
        dataAccessService.findById(id)
            .map { copy(it, false) }
            .flatMap(dataAccessService::update)

    override fun isVerifiedById(id: String): Mono<Boolean> =
        dataAccessService.findById(id)
            .map(isVerified)
}
