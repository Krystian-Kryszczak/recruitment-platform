package krystian.kryszczak.recruitment.controller.api.job.offer

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.authentication.Authentication
import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import jakarta.validation.Valid
import krystian.kryszczak.recruitment.controller.api.ID_PATTERN
import krystian.kryszczak.recruitment.model.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.job.offer.JobOfferQuery
import krystian.kryszczak.recruitment.model.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.job.offer.JobOfferService

@PermitAll
@Controller("api/v1/job/offers/")
@ExecuteOn(TaskExecutors.BLOCKING)
open class JobOfferController(private val service: JobOfferService) {
    @Get("/search/")
    open fun search(@Valid @RequestBean bean: JobOfferQuery) = service.search(bean)

    @Get("/{data}")
    fun findByPathOrId(data: String) = service.findByPathOrId(data)

    @Get("/employer/{id}{/page}")
    fun getByEmployerId(id: String, page: Int?, authentication: Authentication?) = service.findByEmployerId(id, page, authentication)

    @RolesAllowed("EMPLOYER")
    @Get("/own{/page}")
    fun getOwn(page: Int?, authentication: Authentication) = service.findByEmployerAuth(page, authentication)

    @Status(HttpStatus.CREATED)
    @RolesAllowed("EMPLOYER")
    @Post
    open fun add(@Body @Valid body: JobOfferCreationForm, auth: Authentication) = service.employerAdd(body, auth)

    @Status(HttpStatus.ACCEPTED)
    @RolesAllowed("EMPLOYER")
    @Put("/{id:$ID_PATTERN}")
    open fun modify(id: String, @Body @Valid form: JobOfferUpdateForm, authentication: Authentication) =
        service.employerUpdate(id, form, authentication)

    @Status(HttpStatus.ACCEPTED)
    @RolesAllowed("EMPLOYER")
    @Delete("/{id:$ID_PATTERN}")
    fun remove(id: String, authentication: Authentication) = service.employerRemove(id, authentication)
}
