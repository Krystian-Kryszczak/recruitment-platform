package krystian.kryszczak.recruitment.service.text

import io.micronaut.http.MediaType
import reactor.core.publisher.Mono
import java.io.File
import java.io.InputStream

interface TextExtractionService {
    fun extractText(file: File): Mono<String>
    fun extractText(inputStream: InputStream, mediaType: MediaType): Mono<String>
    fun extractText(bytes: ByteArray, mediaType: MediaType): Mono<String>
}
