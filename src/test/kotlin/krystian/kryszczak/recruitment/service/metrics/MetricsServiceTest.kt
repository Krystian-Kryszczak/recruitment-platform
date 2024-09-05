package krystian.kryszczak.recruitment.service.metrics

import io.kotest.core.spec.style.FreeSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.service.moderation.ModerationService

@MicronautTest(transactional = false)
class MetricsServiceTest(moderationService: ModerationService) : FreeSpec({
    "metrics service test" - {
        // TODO
    }
})
