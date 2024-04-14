package krystian.kryszczak.recruitment.controller.api.job.application

import io.kotest.core.spec.style.FreeSpec
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.job.application.JobApplicationService
import reactor.core.publisher.Flux

@MicronautTest(transactional = false)
class JobApplicationControllerTest : FreeSpec({
    "job application controller" - {
        //
    }
}) {
    @MockBean(JobApplicationService::class)
    fun service(): JobApplicationService { // TODO
        val service = mockk<JobApplicationService>()

        every { service.findSentByCandidateClient(any()) } returns Flux.empty()

        return service
    }
}
