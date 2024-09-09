package krystian.kryszczak.recruitment.database.mongodb.repository.security.refresh

import io.micronaut.data.mongodb.annotation.MongoRepository
import jakarta.validation.constraints.NotBlank
import krystian.kryszczak.recruitment.model.security.refresh.RefreshToken
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import reactor.core.publisher.Mono

@MongoRepository
interface RefreshTokenRepository : CrudRepositoryBase<RefreshToken, String> {
    fun findByRefreshToken(@NotBlank refreshToken: String): Mono<RefreshToken>

    fun updateByUsername(@NotBlank username: String, revoked: Boolean): Mono<Long>
}
