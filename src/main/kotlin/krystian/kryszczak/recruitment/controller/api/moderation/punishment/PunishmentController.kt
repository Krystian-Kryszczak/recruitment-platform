package krystian.kryszczak.recruitment.controller.api.moderation.punishment

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.service.management.punishment.PunishmentService

@RolesAllowed("ADMIN")
@Controller("api/v1/moderation/punishment/")
@ExecuteOn(TaskExecutors.IO)
class PunishmentController(private val punishmentService: PunishmentService) {
    @Status(HttpStatus.ACCEPTED)
    @Post("/ban/employer/{id}")
    fun banEmployer(id: String) = punishmentService.banEmployerById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/unban/employer/{id}")
    fun unbanEmployer(id: String) = punishmentService.unbanEmployerById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/is-banned/employer/{id}")
    fun isBannedEmployer(id: String) = punishmentService.isEmployerBannedById(id)


    @Status(HttpStatus.ACCEPTED)
    @Post("/ban/candidate/{id}")
    fun banCandidate(id: String) = punishmentService.banCandidateById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/unban/candidate/{id}")
    fun unbanCandidate(id: String) = punishmentService.unbanCandidateById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/is-banned/candidate/{id}")
    fun isBannedCandidate(id: String) = punishmentService.isCandidateBannedById(id)


    @Status(HttpStatus.ACCEPTED)
    @Post("/ban/job-offer/{id}")
    fun banJobOffer(id: String) = punishmentService.banJobOfferById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/unban/job-offer/{id}")
    fun unbanJobOffer(id: String) = punishmentService.unbanJobOfferById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/is-banned/job-offer/{id}")
    fun isBannedJobOffer(id: String) = punishmentService.isJobOfferBannedById(id)


    @Status(HttpStatus.ACCEPTED)
    @Post("/ban/job-application/{id}")
    fun banJobApplication(id: String) = punishmentService.banJobApplicationById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/unban/job-application/{id}")
    fun unbanJobApplication(id: String) = punishmentService.unbanJobApplicationById(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/is-banned/job-application/{id}")
    fun isBannedJobApplication(id: String) = punishmentService.isJobApplicationBannedById(id)
}
