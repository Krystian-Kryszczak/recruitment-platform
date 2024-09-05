package krystian.kryszczak.recruitment.service.admin

import jakarta.inject.Singleton
import reactor.core.publisher.Mono

@Singleton
class AdminServiceImpl : AdminService {
    override fun banEmployer(id: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun pardonEmployer(id: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun banCandidate(id: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun pardonCandidate(id: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}
