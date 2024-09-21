package krystian.kryszczak.recruitment.service.management.punishment

import reactor.core.publisher.Mono

interface PunishmentService {
    fun isEmployerBannedById(id: String): Mono<Boolean>
    fun banEmployerById(id: String): Mono<Boolean>
    fun unbanEmployerById(id: String): Mono<Boolean>

    fun isCandidateBannedById(id: String): Mono<Boolean>
    fun banCandidateById(id: String): Mono<Boolean>
    fun unbanCandidateById(id: String): Mono<Boolean>

    fun isJobOfferBannedById(id: String): Mono<Boolean>
    fun banJobOfferById(id: String): Mono<Boolean>
    fun unbanJobOfferById(id: String): Mono<Boolean>

    fun isJobApplicationBannedById(id: String): Mono<Boolean>
    fun banJobApplicationById(id: String): Mono<Boolean>
    fun unbanJobApplicationById(id: String): Mono<Boolean>
}
