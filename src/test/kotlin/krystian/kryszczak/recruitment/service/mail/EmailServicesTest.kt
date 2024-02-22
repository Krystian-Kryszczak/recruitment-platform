package krystian.kryszczak.recruitment.service.mail

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.email.javamail.sender.authentication.JavaMailAuthenticationConfiguration
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.mail.search.SubjectTerm
import krystian.kryszczak.recruitment.extension.mail.readContent
import krystian.kryszczak.recruitment.service.mail.pop.PopEmailService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpEmailService
import java.util.UUID

@MicronautTest
class EmailServicesTest(smtpEmailService: SmtpEmailService, popEmailService: PopEmailService, mailAuthConfig: JavaMailAuthenticationConfiguration) : StringSpec({
    val receiver: String = mailAuthConfig.username

    "the message sent by the SMTP email service should be available to the POP3 email service" {
        // given
        val messageId = UUID.randomUUID()
        val subject = "Email sent using string content - SMTP email service test ($messageId)"
        val content = "Hello world!"

        // when
        shouldNotThrowAny {
            smtpEmailService.send(
                receiver,
                subject,
                content
            )
        }

        // then
        popEmailService.searchMessages(term = SubjectTerm(subject)).blockFirst()
            .shouldNotBeNull()
            .readContent() shouldBe content
    }
})
