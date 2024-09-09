package krystian.kryszczak.recruitment.controller.api.pricing

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.annotation.security.PermitAll
import krystian.kryszczak.recruitment.service.pricing.PricingService

@PermitAll
@Controller("api/v1/pricing/")
class PricingController(private val pricingService: PricingService) {
    @Get
    fun getAll() = pricingService.findAll()
}
