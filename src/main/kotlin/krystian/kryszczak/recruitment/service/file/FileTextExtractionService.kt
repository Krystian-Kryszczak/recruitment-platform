package krystian.kryszczak.recruitment.service.file

import reactor.core.publisher.Mono
import java.io.File

interface FileTextExtractionService {
    fun extractText(file: File): Mono<String>
}
