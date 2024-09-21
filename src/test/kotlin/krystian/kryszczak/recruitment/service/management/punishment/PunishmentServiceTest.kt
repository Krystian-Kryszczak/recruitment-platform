package krystian.kryszczak.recruitment.service.management.punishment

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.mpp.uniqueId
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.RestrictableDataAccessService
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.exhibit.job.application.JobApplicationService
import krystian.kryszczak.recruitment.service.exhibit.job.offer.JobOfferService
import reactor.core.publisher.Mono

@MicronautTest(transactional = false)
class PunishmentServiceTest(punishmentService: PunishmentService) : FreeSpec({
    "punishment service test" - {
        fun doTest(action: PunishmentService.(id: String) -> Mono<Boolean>) {
            // given
            val id = uniqueId()

            // when
            val result = action(punishmentService, id)
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBeTrue()
        }

        "employer" - {
            "is banned by id" {
                doTest(PunishmentService::isEmployerBannedById)
            }

            "ban by id" {
                doTest(PunishmentService::banEmployerById)
            }

            "unban by id" {
                doTest(PunishmentService::unbanEmployerById)
            }
        }

        "candidate" - {
            "is banned by id" {
                doTest(PunishmentService::isCandidateBannedById)
            }

            "ban by id" {
                doTest(PunishmentService::banCandidateById)
            }

            "unban by id" {
                doTest(PunishmentService::unbanCandidateById)
            }
        }

        "job offer" - {
            "is banned by id" {
                doTest(PunishmentService::isJobOfferBannedById)
            }

            "ban by id" {
                doTest(PunishmentService::banJobOfferById)
            }

            "unban by id" {
                doTest(PunishmentService::unbanJobOfferById)
            }
        }

        "job application" - {
            "is banned by id" {
                doTest(PunishmentService::isJobApplicationBannedById)
            }

            "ban by id" {
                doTest(PunishmentService::banJobOfferById)
            }

            "unban by id" {
                doTest(PunishmentService::unbanJobOfferById)
            }
        }
    }
}) {
    private inline fun <reified T : RestrictableDataAccessService<*, *, String>> reifiedMock() = mockk<T> {
        every { isBannedById(any()) } returns Mono.just(true)
        every { banById(any()) } returns Mono.just(true)
        every { unbanById(any()) } returns Mono.just(true)
    }

    @MockBean(EmployerService::class)
    fun employerService(): EmployerService = reifiedMock()

    @MockBean(CandidateService::class)
    fun candidateService(): CandidateService = reifiedMock()

    @MockBean(JobOfferService::class)
    fun jobOfferService(): JobOfferService = reifiedMock()

    @MockBean(JobApplicationService::class)
    fun jobApplicationService(): JobApplicationService = reifiedMock()
}
