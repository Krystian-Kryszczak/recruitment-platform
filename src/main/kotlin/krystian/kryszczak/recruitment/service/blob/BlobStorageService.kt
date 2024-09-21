package krystian.kryszczak.recruitment.service.blob

import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.http.server.types.files.StreamedFile
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BlobStorageService {
    fun save(id: String, files: Publisher<StreamingFileUpload>): Mono<String>
    fun getById(id: String): Flux<StreamedFile>
    fun deleteById(id: String): Mono<Boolean>
}
