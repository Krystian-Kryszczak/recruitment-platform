package krystian.kryszczak.recruitment.security.generator.reset

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(transactional = false)
class ResetPasswordCodeGeneratorTest(generator: ResetPasswordCodeGenerator): StringSpec({

    val generatedCode = generator.generateCode()

    "generated code length should be 8" {
        generatedCode.length shouldBe 8
    }

    "generated code should contain letters" {
        generatedCode shouldContain Regex("([a-z]|[A-Z])")
    }
})
