package krystian.kryszczak.recruitment.service.moderation

import jakarta.validation.constraints.NotBlank
import reactor.core.publisher.Mono

interface ModerationService {
    fun checkIfInputIsHarmful(@NotBlank input: String): Mono<Boolean>
}
