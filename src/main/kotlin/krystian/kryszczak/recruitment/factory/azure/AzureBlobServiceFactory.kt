package krystian.kryszczak.recruitment.factory.azure

import com.azure.core.credential.TokenCredential
import com.azure.storage.blob.BlobServiceAsyncClient
import com.azure.storage.blob.BlobServiceClientBuilder
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class AzureBlobServiceFactory {
    @Singleton
    fun blobServiceClient(tokenCredential: TokenCredential): BlobServiceAsyncClient {
        return BlobServiceClientBuilder()
            .credential(tokenCredential)
            .endpoint(System.getenv("AZURE_BLOB_ENDPOINT"))
            //.httpClient()
            .buildAsyncClient()
    }
}
