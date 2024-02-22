package krystian.kryszczak.recruitment.service.mail.pop

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.micronaut.email.javamail.sender.authentication.JavaMailAuthenticationConfiguration
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.mail.search.FromStringTerm

@MicronautTest
class PopEmailServiceTest(popEmailService: PopEmailService, mailAuthConfig: JavaMailAuthenticationConfiguration): StringSpec({
    "searchMessages should not be empty" {
        popEmailService.searchMessages(term = FromStringTerm(mailAuthConfig.username)).toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessages should not be empty" {
        popEmailService.receiveMessages().toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessages with msgnums should not be empty" {
        popEmailService.receiveMessages(msgnums = IntArray(5) { it * 1 + 1 }).toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessages with start and end should not be empty" {
        popEmailService.receiveMessages(start = 1, end = 5).toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessage should not be null" {
        popEmailService.receiveMessage(msgnum = 5).block()
            .shouldNotBeNull()
    }
})
