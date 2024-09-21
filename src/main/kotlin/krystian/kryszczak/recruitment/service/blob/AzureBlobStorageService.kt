package krystian.kryszczak.recruitment.service.blob

import com.azure.core.util.BinaryData
import com.azure.storage.blob.BlobServiceAsyncClient
import io.micronaut.http.MediaType
import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.http.server.types.files.StreamedFile
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class AzureBlobStorageService(private val blobServiceClient: BlobServiceAsyncClient) : BlobStorageService {
    override fun save(id: String, files: Publisher<StreamingFileUpload>): Mono<String> {
        val containerClient = blobServiceClient.getBlobContainerAsyncClient(id)
        return Flux.from(files).flatMap {
            containerClient.getBlobAsyncClient(it.filename)
                .upload(BinaryData.fromStream(it.asInputStream()))
        }.then(Mono.just(id))
    }

    override fun getById(id: String): Flux<StreamedFile> {
        val containerClient = blobServiceClient.getBlobContainerAsyncClient(id)
        return containerClient.listBlobs().flatMap { item ->
            containerClient.getBlobAsyncClient(item.name)
                .downloadContent()
                .map { StreamedFile(it.toStream(), MediaType.forFilename(item.name)) }
        }
    }

    override fun deleteById(id: String): Mono<Boolean> = blobServiceClient.deleteBlobContainerIfExists(id)
}
