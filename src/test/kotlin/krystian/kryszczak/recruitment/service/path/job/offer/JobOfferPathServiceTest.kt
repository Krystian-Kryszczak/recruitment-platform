package krystian.kryszczak.recruitment.service.path.job.offer

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.string.shouldNotContain
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.test.mock.jobOfferMock

@MicronautTest(transactional = false)
class JobOfferPathServiceTest(pathService: JobOfferPathService) : FreeSpec({
    "path service test" - {
//        "create path for (job offer)" {
//            // given
//            val jobOffer = jobOfferMock
//
//            // when
//            val result = pathService.createPathIfThereNoPathExists(jobOffer)
//                .block()
//
//            // then
//            result.shouldNotBeNull()
//                .shouldNotBeBlank()
//                .shouldNotContain(" ")
//        }
//
//        "create path for (form)" { // TODO
//            // given
//            val jobOffer = jobOfferMock
//
//            // when
//            val result = pathService.createPathIfThereNoPathExists(jobOffer)
//                .block()
//
//            // then
//            result.shouldNotBeNull()
//                .shouldNotBeBlank()
//                .shouldNotContain(" ")
//        }
    }
}) {
    @MockBean(EmployerService::class)
    fun employerService(): EmployerService {
        val service = mockk<EmployerService>()
        // TODO
        return service
    }
//
//    @MockBean(JobOfferService::class)
//    fun jobOfferService(): JobOfferService {
//        val service = mockk<JobOfferService>()
//        // TODO
//        return service
//    }
}
