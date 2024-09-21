package krystian.kryszczak.recruitment.service.path.job.offer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.service.being.employer.EmployerService
import reactor.core.publisher.Mono

@Singleton
class DefaultJobOfferPathService(private val employerService: EmployerService) : JobOfferPathService {
    override fun generatePath(jobOfferId: String, jobOfferTitle: String, employerId: String): Mono<String> =
        employerService.getEmployerName(employerId).map {
            ("${jobOfferTitle.trim()}-${it.trim()}-${jobOfferId.trim()}").trim().replace(" ", "-").lowercase()
        }
}
