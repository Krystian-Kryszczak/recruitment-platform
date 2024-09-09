package krystian.kryszczak.recruitment.database.mongodb.event

import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.service.pricing.PricingService
import reactor.core.scheduler.Schedulers

@Singleton
class MongoDBStartup(private val pricingService: PricingService) {
    @EventListener
    fun onStartupEvent(event: StartupEvent) {
        saveDefaultTiersIfNoneExists()
    }

    private fun saveDefaultTiersIfNoneExists() {
        pricingService.saveDefaultTiersIfNoneExists()
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe()
    }
}
