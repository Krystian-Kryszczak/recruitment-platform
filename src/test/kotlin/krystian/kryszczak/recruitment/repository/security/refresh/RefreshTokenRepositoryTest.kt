package krystian.kryszczak.recruitment.repository.security.refresh

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.security.refresh.RefreshToken
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest
import java.time.Duration.ofHours
import java.time.Instant.now

@MicronautTest(transactional = false)
class RefreshTokenRepositoryTest(repository: RefreshTokenRepository) : CrudRepositoryBaseTest<RefreshToken>(
repository,
arrayOf(
    RefreshToken(null, "Eliot", "token-0", false, now() + ofHours(1)),
    RefreshToken(null, "Darlene", "token-1", true, now() + ofHours(1)),
    RefreshToken(null, "Leon", "token-2", false, now() + ofHours(2)),
    RefreshToken(null, "Phillip", "token-3", true, now() + ofHours(3))
), { it.copy(username = "${it.username} - ${it.username}") },
{ item, id -> item.copy(id = id) }, {
    "refresh token repository tests" - {
        "find by refresh token" {
            // given
            val refreshToken = repository.save(
                RefreshToken(null, "jack@smith.com", uniqueId(), false)
            ).block().shouldNotBeNull()

            // when
            val result = repository.findByRefreshToken(refreshToken.refreshToken).block()

            // then
            result.shouldNotBeNull() shouldBe refreshToken
        }

        "update by username" {
            // given
            val refreshToken = repository.save(
                RefreshToken(null, "jack@smith.com", uniqueId(), false)
            ).block().shouldNotBeNull()

            // when
            repository.updateByUsername(refreshToken.username, true).block()
            val result = repository.findById(refreshToken.id).block()

            // then
            result.shouldNotBeNull() shouldBe refreshToken.copy(revoked = true)
        }
    }
})
