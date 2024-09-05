package krystian.kryszczak.recruitment.controller.api.admin

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.service.admin.AdminService

@RolesAllowed("ADMIN")
@Controller("api/v1/admin/")
@ExecuteOn(TaskExecutors.IO)
class AdminController(private val adminService: AdminService) {
    @Status(HttpStatus.ACCEPTED)
    @Post("/employer/ban/{id}")
    fun banEmployer(id: String) = adminService.banEmployer(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/employer/pardon/{id}")
    fun pardonEmployer(id: String) = adminService.pardonEmployer(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/candidate/ban/{id}")
    fun banCandidate(id: String) = adminService.banCandidate(id)

    @Status(HttpStatus.ACCEPTED)
    @Post("/candidate/pardon/{id}")
    fun pardonCandidate(id: String) = adminService.pardonCandidate(id)
}
