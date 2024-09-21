package krystian.kryszczak.recruitment.service.text

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.micronaut.http.MediaType
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import java.io.File

@MicronautTest(transactional = false)
class TextExtractionServiceTest(textExtractionService: TextExtractionService) : FreeSpec({
    "text extraction service test" - {
        "extract text" - {
            val given = mapOf(
                File("./src/test/resources/assets/text/Hello_world!.txt") to "Hello world!\n",
                File("./src/test/resources/assets/pdf/Hello_world!.pdf") to "Hello world!\n"
            )

            fun testResults(actual: String?, excepted: String) =
                actual.shouldNotBeNull()
                    .shouldNotBeBlank()
                    .shouldBe(excepted)

            "from file" {
                for ((file, excepted) in given) {
                    // when
                    val result = textExtractionService.extractText(file)
                        .block()

                    // then
                    testResults(result, excepted)
                }
            }

            "from input stream" {
                for ((file, excepted) in given) {
                    // when
                    val result = textExtractionService.extractText(file.inputStream(), MediaType.forFilename(file.name))
                        .block()

                    // then
                    testResults(result, excepted)
                }
            }

            "from byte array" {
                for ((file, excepted) in given) {
                    // when
                    val result = textExtractionService.extractText(file.readBytes(), MediaType.forFilename(file.name))
                        .block()

                    // then
                    testResults(result, excepted)
                }
            }
        }
    }
})
