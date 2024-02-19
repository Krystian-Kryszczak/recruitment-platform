package krystian.kryszczak.recruitment

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.kotest.core.spec.style.StringSpec

@MicronautTest(transactional = false)
class RecruitmentPlatformTest(application: EmbeddedApplication<*>): StringSpec({
    "test the server is running" {
        assert(application.isRunning)
    }
})
