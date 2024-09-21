package krystian.kryszczak.recruitment.service.management.moderation

import reactor.core.publisher.Mono

interface ModerationService {
    fun checkIfInputIsHarmful(input: String, id: String): Mono<Boolean>
}
