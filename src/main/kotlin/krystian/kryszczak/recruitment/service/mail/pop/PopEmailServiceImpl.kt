package krystian.kryszczak.recruitment.service.mail.pop

import io.micronaut.context.exceptions.ConfigurationException
import jakarta.inject.Singleton
import io.micronaut.email.javamail.sender.authentication.JavaMailAuthenticationConfiguration
import jakarta.mail.Folder
import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.Store
import jakarta.mail.search.SearchTerm
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.Properties

@Singleton
class PopEmailServiceImpl(private val session: Session, private val mailAuthConfig: JavaMailAuthenticationConfiguration) : PopEmailService {
    private val properties: Properties = session.properties

    private inline fun useStore(body: (Store) -> Unit) {
        session.getStore("pop3").use { store ->
            store.connect(
                properties["mail.pop3.host"] as String?
                    ?: throw ConfigurationException("The mail.pop3.host property is missing."),
                mailAuthConfig.username,
                mailAuthConfig.password
            )
            body(store)
        }
    }

    private inline fun useFolder(name: String? = null, body: (Folder) -> Unit) {
        useStore { store ->
            (if (name != null) store.getFolder(name) else store.getFolder("INBOX")).use {
                it.open(Folder.READ_ONLY)
                body(it)
            }
        }
    }

    private inline fun receiveMessages(folder: String?, body: (Folder) -> Array<Message>): Flux<Message> {
        useFolder(folder) {
            return Flux.fromArray(body(it))
        }
        return Flux.empty()
    }

    override fun searchMessages(folder: String?, term: SearchTerm) = receiveMessages(folder) { it.search(term) }

    override fun receiveMessages(folder: String?) = receiveMessages(folder) { it.messages }

    override fun receiveMessages(folder: String?, msgnums: IntArray) = receiveMessages(folder) { it.getMessages(msgnums) }

    override fun receiveMessages(folder: String?, start: Int, end: Int) = receiveMessages(folder) { it.getMessages(start, end) }

    override fun receiveMessage(folder: String?, msgnum: Int): Mono<Message> = receiveMessages(folder) { arrayOf(it.getMessage(msgnum)) }.single()
}
