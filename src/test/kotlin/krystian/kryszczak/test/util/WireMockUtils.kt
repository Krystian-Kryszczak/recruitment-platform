package krystian.kryszczak.test.util

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.wiremock.integrations.testcontainers.WireMockContainer
import java.util.function.Consumer

object WireMockUtils {
    private val wiremockServer: WireMockContainer = WireMockContainer("wiremock/wiremock:3.3.1-alpine")
        .withMappingFromResource("open-ai", "wiremock/stubs/openai.com-stubs.json")

    private val properties: Map<String, Any>
        get() = mapOf("micronaut.http.services.open-ai.url" to wiremockServer.baseUrl)

    fun wireMockEnv(contextConsumer: Consumer<ApplicationContext>) {
        wiremockServer.start()
        ApplicationContext.run(EmbeddedServer::class.java, properties).use { server ->
            contextConsumer.accept(server.applicationContext)
        }
    }
}
