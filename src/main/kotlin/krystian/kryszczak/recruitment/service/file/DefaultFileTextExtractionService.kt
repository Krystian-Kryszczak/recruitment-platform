package krystian.kryszczak.recruitment.service.file

import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.io.File

@Singleton
class DefaultFileTextExtractionService : FileTextExtractionService {
    override fun extractText(file: File): Mono<String> {
        TODO("Not yet implemented")
    }
}
