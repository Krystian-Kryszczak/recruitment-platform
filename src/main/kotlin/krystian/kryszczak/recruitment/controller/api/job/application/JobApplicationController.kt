package krystian.kryszczak.recruitment.controller.api.job.application

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Status
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.authentication.Authentication
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.ID_PATTERN
import krystian.kryszczak.recruitment.service.job.application.JobApplicationService

@Controller("api/v1/job/applications/")
@ExecuteOn(TaskExecutors.IO)
class JobApplicationController(private val service: JobApplicationService) {
    @RolesAllowed("EMPLOYER")
    @Get("/{id:$ID_PATTERN}")
    fun get(@PathVariable id: String, authentication: Authentication) =
        service.findByIdForEmployerClient(id, authentication)

    @RolesAllowed("EMPLOYER")
    @Get("/offer/{id:$ID_PATTERN}")
    fun getByOfferId(@PathVariable id: String, authentication: Authentication) =
        service.findByOfferIdForEmployerClient(id, authentication)

    @RolesAllowed("CANDIDATE")
    @Get("/own{/page}")
    fun findOwn(@PathVariable(defaultValue = "0") page: Int, authentication: Authentication) =
        service.findPublishedByCandidateClient(authentication)

    @Status(HttpStatus.ACCEPTED)
    @RolesAllowed("CANDIDATE")
    @Delete("/{id:$ID_PATTERN}")
    fun delete(@PathVariable id: String, authentication: Authentication) =
        service.deleteOwnByIdForCandidateClient(id, authentication)
}
