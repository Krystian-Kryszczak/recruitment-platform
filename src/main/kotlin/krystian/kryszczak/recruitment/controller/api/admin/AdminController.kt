package krystian.kryszczak.recruitment.controller.api.admin

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.service.admin.AdminService

@RolesAllowed("ADMIN")
@Controller("api/v1/admin")
@ExecuteOn(TaskExecutors.BLOCKING)
class AdminController(private val adminService: AdminService)
