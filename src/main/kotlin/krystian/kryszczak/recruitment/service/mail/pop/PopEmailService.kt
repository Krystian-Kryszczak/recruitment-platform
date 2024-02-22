package krystian.kryszczak.recruitment.service.mail.pop

import jakarta.mail.Message
import jakarta.mail.search.SearchTerm
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PopEmailService {
    fun searchMessages(folder: String? = null, term: SearchTerm): Flux<Message>
    fun receiveMessages(folder: String? = null): Flux<Message>
    fun receiveMessages(folder: String? = null, msgnums: IntArray): Flux<Message>
    fun receiveMessages(folder: String? = null, start: Int, end: Int): Flux<Message>
    fun receiveMessage(folder: String? = null, msgnum: Int): Mono<Message>
}
