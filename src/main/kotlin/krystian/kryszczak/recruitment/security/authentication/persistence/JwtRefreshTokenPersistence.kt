package krystian.kryszczak.recruitment.security.authentication.persistence

import io.micronaut.security.authentication.Authentication
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.security.refresh.RefreshToken
import krystian.kryszczak.recruitment.database.mongodb.repository.security.refresh.RefreshTokenRepository
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Singleton
class JwtRefreshTokenPersistence(private val repository: RefreshTokenRepository) : RefreshTokenPersistence {
    override fun persistToken(event: RefreshTokenGeneratedEvent?) {
        if (event?.refreshToken != null && event.authentication?.name != null) {
            repository.save(RefreshToken(null, event.authentication.name, event.refreshToken, false))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe()
        }
    }

    override fun getAuthentication(refreshToken: String): Publisher<Authentication> =
        repository.findByRefreshToken(refreshToken).flatMap { tokenOpt ->
            val (_, username, _, revoked) = tokenOpt
            if (revoked) {
                return@flatMap Mono.error(OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null))
            } else {
                return@flatMap Mono.just(Authentication.build(username))
            }
        }.switchIfEmpty(Mono.error(OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null)))
}
