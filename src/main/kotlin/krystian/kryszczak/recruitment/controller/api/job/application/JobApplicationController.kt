package krystian.kryszczak.recruitment.controller.api.job.application

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.authentication.Authentication
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.ID_PATTERN
import krystian.kryszczak.recruitment.service.job.application.JobApplicationService

@Controller("api/v1/job/applications/")
@ExecuteOn(TaskExecutors.BLOCKING)
class JobApplicationController(private val service: JobApplicationService) { // TODO get candidate applications and cancel, pageination
    @RolesAllowed("EMPLOYER")
    @Get("offer/{id:$ID_PATTERN}")
    fun getForOffer(@PathVariable id: String, authentication: Authentication) = service.findByEmployerClient(id, authentication)

    @RolesAllowed("EMPLOYER")
    @Get("/{id:$ID_PATTERN}")
    fun get(@PathVariable id: String, authentication: Authentication) = service.findByEmployerClient(id, authentication)

    @RolesAllowed("CANDIDATE")
    @Get("/own{/page}")
    fun getOwn(page: Int?, authentication: Authentication) = service.findSentByCandidateClient(authentication)

    @RolesAllowed("CANDIDATE")
    @Get("/own/{id:$ID_PATTERN}")
    fun findOwn(@PathVariable id: String, authentication: Authentication) =
        service.findByIdSentByCandidateClient(id, authentication)

    @RolesAllowed("CANDIDATE")
    @Delete("/own/{id:$ID_PATTERN}")
    fun cancelOwn(@PathVariable id: String, authentication: Authentication) =
        service.cancelOwnById(id, authentication)
}
