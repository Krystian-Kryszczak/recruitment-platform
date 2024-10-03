package krystian.kryszczak.recruitment.controller.api.job.application

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.ID_PATTERN
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.service.exhibit.job.application.JobApplicationService

@Controller("api/v1/job/applications/")
@ExecuteOn(TaskExecutors.IO)
class JobApplicationController(private val service: JobApplicationService) {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post
    fun add(@Body form: JobApplicationCreationForm, authentication: Authentication?) =
        service.addByCandidate(form, authentication)

    @RolesAllowed("EMPLOYER")
    @Get("/{id:$ID_PATTERN}")
    fun get(@PathVariable id: String, authentication: Authentication) =
        service.findByIdForEmployerClient(id, authentication)

    @RolesAllowed("EMPLOYER")
    @Get("/offer/{id:$ID_PATTERN}{/page}")
    fun getByOfferId(@PathVariable id: String, @PathVariable(defaultValue = "0") page: Int, authentication: Authentication) =
        service.findByOfferIdForEmployerClient(id, authentication, page)

    @RolesAllowed("CANDIDATE")
    @Get("/own{/page}")
    fun findOwn(@PathVariable(defaultValue = "0") page: Int, authentication: Authentication) =
        service.findPublishedByCandidateClient(authentication, page)

    @Status(HttpStatus.ACCEPTED)
    @RolesAllowed("CANDIDATE")
    @Delete("/{id:$ID_PATTERN}")
    fun delete(@PathVariable id: String, authentication: Authentication) =
        service.deleteOwnByIdForCandidateClient(id, authentication)
}
