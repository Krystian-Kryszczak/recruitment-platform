package krystian.kryszczak.recruitment.security.generator.reset

import jakarta.inject.Singleton
import org.apache.commons.lang3.RandomStringUtils

@Singleton
class ResetPasswordCodeGeneratorImpl : ResetPasswordCodeGenerator {
    override fun generateCode(): String = RandomStringUtils.random(8, true, true)
}
