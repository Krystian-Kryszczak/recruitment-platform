package krystian.kryszczak.recruitment.service.management.punishment

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import krystian.kryszczak.recruitment.service.exhibit.job.application.JobApplicationService
import krystian.kryszczak.recruitment.service.exhibit.job.offer.JobOfferService
import reactor.core.publisher.Mono

@Singleton
class DefaultPunishmentService(
    private val employerService: EmployerService,
    private val candidateService: CandidateService,
    private val jobOfferService: JobOfferService,
    private val jobApplicationService: JobApplicationService
) : PunishmentService {
    override fun isEmployerBannedById(id: String): Mono<Boolean> = employerService.isBannedById(id)
    override fun banEmployerById(id: String): Mono<Boolean> = employerService.banById(id)
    override fun unbanEmployerById(id: String): Mono<Boolean> = employerService.unbanById(id)

    override fun isCandidateBannedById(id: String): Mono<Boolean> = candidateService.isBannedById(id)
    override fun banCandidateById(id: String): Mono<Boolean> = candidateService.banById(id)
    override fun unbanCandidateById(id: String): Mono<Boolean> = candidateService.unbanById(id)

    override fun isJobOfferBannedById(id: String): Mono<Boolean> = jobOfferService.isBannedById(id)
    override fun banJobOfferById(id: String): Mono<Boolean> = jobOfferService.banById(id)
    override fun unbanJobOfferById(id: String): Mono<Boolean> = jobOfferService.unbanById(id)

    override fun isJobApplicationBannedById(id: String): Mono<Boolean> = jobApplicationService.isBannedById(id)
    override fun banJobApplicationById(id: String): Mono<Boolean> = jobApplicationService.banById(id)
    override fun unbanJobApplicationById(id: String): Mono<Boolean> = jobApplicationService.unbanById(id)
}
