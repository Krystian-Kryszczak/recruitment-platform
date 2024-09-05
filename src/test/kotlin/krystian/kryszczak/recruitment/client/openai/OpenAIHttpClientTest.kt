package krystian.kryszczak.recruitment.client.openai

import io.kotest.core.spec.style.FreeSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class OpenAIHttpClientTest(httpClient: OpenAIHttpClient) : FreeSpec({
    "open ai http client test" - {
        // TODO
    }
}) // TODO wiremock
