package krystian.kryszczak.recruitment.service.path.job.offer

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.string.shouldNotContain
import io.kotest.mpp.uniqueId
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class JobOfferPathServiceTest(pathService: JobOfferPathService) : FreeSpec({
    "path service test" - {
        "generate path" {
            // given
            val jobOfferId = uniqueId()
            val jobOfferTitle = "Senior Kotlin Developer"
            val employerId = uniqueId()

            // when
            val result = pathService.generatePath(jobOfferId, jobOfferTitle, employerId)
                .block()

            // then
            result.shouldNotBeNull()
                .shouldNotBeBlank()
                .shouldNotContain(" ")
                .shouldContain("senior-kotlin-developer")
                .shouldContain("e-corp")
                .shouldContain(jobOfferId)
        }
    }
}) {
    @MockBean(EmployerService::class)
    fun employerService(): EmployerService {
        return mockk<EmployerService> {
            every { getEmployerName(any()) } returns Mono.just("E-Corp")
        }
    }
}
