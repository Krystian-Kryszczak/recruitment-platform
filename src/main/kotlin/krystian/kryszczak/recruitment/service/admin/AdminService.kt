package krystian.kryszczak.recruitment.service.admin

import reactor.core.publisher.Mono

interface AdminService {
    fun banEmployer(id: String): Mono<Boolean>
    fun pardonEmployer(id: String): Mono<Boolean>
    fun banCandidate(id: String): Mono<Boolean>
    fun pardonCandidate(id: String): Mono<Boolean>
}
