package krystian.kryszczak.recruitment.service.path.job.offer

import reactor.core.publisher.Mono

interface JobOfferPathService {
    fun generatePath(jobOfferId: String, jobOfferTitle: String, employerId: String): Mono<String>
}
