package krystian.kryszczak.recruitment.service.blob

import io.micronaut.http.multipart.StreamingFileUpload
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
class DefaultBlobStorageService : BlobStorageService {
    // TODO
    override fun save(
        id: String,
        files: Publisher<StreamingFileUpload>,
        clientId: String
    ): Mono<String> {
        TODO("Not yet implemented")
    }
}
