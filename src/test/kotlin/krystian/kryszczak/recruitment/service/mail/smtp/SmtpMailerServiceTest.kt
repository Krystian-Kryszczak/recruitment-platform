package krystian.kryszczak.recruitment.service.mail.smtp

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.micronaut.email.BodyType
import io.micronaut.email.MultipartBody
import io.micronaut.email.javamail.sender.authentication.JavaMailAuthenticationConfiguration
import io.micronaut.email.template.TemplateBody
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.micronaut.views.ModelAndView

const val testingTemplate = "testing"

@MicronautTest
class SmtpMailerServiceTest(smtpMailerService: SmtpMailerService, mailAuthConfig: JavaMailAuthenticationConfiguration) : StringSpec({
    val receiver: String = mailAuthConfig.username

    "send with string content should not throw any exception" {
        shouldNotThrowAny {
            smtpMailerService.send(
                receiver,
                "Email sent using string content - SMTP email service test",
                "Hello world!"
            )
        }
    }

    "send with multipart body content should not throw any exception" {
        shouldNotThrowAny {
            smtpMailerService.send(
                receiver,
                "Email sent using multipart body content - SMTP email service test",
                MultipartBody(
                    TemplateBody(BodyType.HTML, ModelAndView("template/$testingTemplate/html.vm", mapOf<String, String>())),
                    TemplateBody(BodyType.TEXT, ModelAndView("template/$testingTemplate/text.vm", mapOf<String, String>()))
                )
            )
        }
    }

    "sendUsingTemplate should not throw any exception" {
        shouldNotThrowAny {
            smtpMailerService.sendUsingTemplate(receiver, "Email sent using template - SMTP email service test",
                testingTemplate, mapOf())
        }
    }

    "sendUserActivationCode should not throw any exception" {
        shouldNotThrowAny {
            smtpMailerService.sendUserActivationCode(receiver, "`test activation code`")
        }
    }

    "sendResetPasswordCode should not throw any exception" {
        shouldNotThrowAny {
            smtpMailerService.sendResetPasswordCode(receiver, "`test reset password code`")
        }
    }

    "sendNewVideoNotification should not throw any exception" {
        shouldNotThrowAny {
            smtpMailerService.sendNewVideoNotification(
                receiver,
                "John Smith",
                "https://picsum.photos/200/300",
                "Send new video notification - SMTP email service test",
                "https://www.youtube.com/"
            )
        }
    }
})
