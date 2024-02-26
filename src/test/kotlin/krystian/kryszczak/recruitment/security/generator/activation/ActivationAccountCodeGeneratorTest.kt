package krystian.kryszczak.recruitment.security.generator.activation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class ActivationAccountCodeGeneratorTest(generator: ActivationCodeGenerator): StringSpec({

    val generatedCode = generator.generateCode()

    "generated activation account code length should be 5" {
        generatedCode.length shouldBe 5
    }

    "generated code should contain letters" {
        generatedCode shouldContain Regex("([a-z]|[A-Z])")
    }
})
