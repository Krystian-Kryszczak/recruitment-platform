package krystian.kryszczak.recruitment.repository.security.refresh

import io.micronaut.data.mongodb.annotation.MongoRepository
import jakarta.validation.constraints.NotBlank
import krystian.kryszczak.recruitment.model.security.refresh.RefreshToken
import krystian.kryszczak.recruitment.repository.CrudRepositoryBase
import reactor.core.publisher.Mono

@MongoRepository
interface RefreshTokenRepository : CrudRepositoryBase<RefreshToken> {
    fun findByRefreshToken(@NotBlank refreshToken: String): Mono<RefreshToken>

    fun updateByUsername(@NotBlank username: String, revoked: Boolean): Mono<Long>
}
