package krystian.kryszczak.recruitment.service.mail.pop

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.micronaut.email.javamail.sender.authentication.JavaMailAuthenticationConfiguration
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.mail.search.FromStringTerm

@MicronautTest
class PopMailerServiceTest(popMailerService: PopMailerService, mailAuthConfig: JavaMailAuthenticationConfiguration): StringSpec({
    "searchMessages should not be empty" {
        popMailerService.searchMessages(term = FromStringTerm(mailAuthConfig.username)).toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessages should not be empty" {
        popMailerService.receiveMessages().toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessages with msgnums should not be empty" {
        popMailerService.receiveMessages(msgnums = IntArray(5) { it * 1 + 1 }).toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessages with start and end should not be empty" {
        popMailerService.receiveMessages(start = 1, end = 5).toIterable()
            .shouldNotBeEmpty()
    }

    "receiveMessage should not be null" {
        popMailerService.receiveMessage(msgnum = 5).block()
            .shouldNotBeNull()
    }
})
