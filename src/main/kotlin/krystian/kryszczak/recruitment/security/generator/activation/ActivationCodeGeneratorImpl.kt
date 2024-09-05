package krystian.kryszczak.recruitment.security.generator.activation

import jakarta.inject.Singleton
import org.apache.commons.lang3.RandomStringUtils

@Singleton
class ActivationCodeGeneratorImpl : ActivationCodeGenerator {
    override fun generateCode(): String = RandomStringUtils.random(5, true, false)
}
