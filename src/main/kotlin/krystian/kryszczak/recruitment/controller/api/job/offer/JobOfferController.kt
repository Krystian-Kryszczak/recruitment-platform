package krystian.kryszczak.recruitment.controller.api.job.offer

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
    @Get
    open fun search(@Valid @RequestBean bean: JobOfferQuery) = service.search(bean)

    @Get("/{id:$ID_PATTERN}")
    fun findById(id: String) = service.findById(id)

    @Get("/{path}")
    fun findByName(path: String) = service.findByPath(path)

    @RolesAllowed("EMPLOYER")
    @Get("/own{/page}")
    fun getOwn(page: Int?, authentication: Authentication) = service.getEmployerOffers(authentication)

    @RolesAllowed("EMPLOYER")
    @Post
    open fun add(@Body @Valid body: JobOfferCreationForm, auth: Authentication) = service.employerAdd(body, auth)

    @RolesAllowed("EMPLOYER")
    @Put("/{id:$ID_PATTERN}")
    fun modify(id: String, form: JobOfferUpdateForm, authentication: Authentication) =
        service.employerUpdate(id, form, authentication)

    @RolesAllowed("EMPLOYER")
    @Delete("/{id:$ID_PATTERN}")
    fun remove(id: String, authentication: Authentication) =
        service.employerRemove(id, authentication)
}
