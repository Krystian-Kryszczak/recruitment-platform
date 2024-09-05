package krystian.kryszczak.recruitment.factory.mail

import io.micronaut.context.annotation.Factory
import io.micronaut.email.javamail.sender.SessionProvider
import jakarta.inject.Singleton
import jakarta.mail.Session

@Factory
class MailFactory {
    @Singleton
    fun session(sessionProvider: SessionProvider): Session = sessionProvider.session()
}
