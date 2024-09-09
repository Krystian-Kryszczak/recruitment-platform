package krystian.kryszczak.recruitment.mapper.exhibit.job.application

import io.micronaut.http.multipart.StreamingFileUpload
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.service.blob.BlobStorageService
import krystian.kryszczak.recruitment.service.moderation.ModerationService
import org.bson.types.ObjectId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class DefaultJobApplicationMapper(
    private val moderationService: ModerationService,
    private val blobStorageService: BlobStorageService
) : JobApplicationMapper { // TODO cvFile moderation
    override fun mapToOriginItem(form: JobApplicationCreationForm, doerId: String): Mono<JobApplication> = with(form) {
        ObjectId.get().toHexString().let { id ->
            Mono.justOrEmpty<String>(messageToRecruiter)
                .flatMap { moderationService.checkIfInputIsHarmful(it, id) }
                .flatMap { map(id, form, doerId, cvFile, it) }
                .switchIfEmpty(map(id, form, doerId, cvFile, false))
        }
    }

    private fun map(id: String, form: JobApplicationCreationForm, doerId: String, cvFile: StreamingFileUpload, banned: Boolean) = with(form) {
        blobStorageService.save(id, Flux.just(cvFile), doerId).map {
            JobApplication(
                id,
                offerId,
                doerId,
                id,
                messageToRecruiter,
                banned,
                null
            )
        }
    }

    override fun mapToOriginItem(actual: JobApplication, form: JobApplicationUpdateForm): Mono<JobApplication> = with(form) {
        Mono.justOrEmpty<String>(messageToRecruiter)
            .flatMap { moderationService.checkIfInputIsHarmful(it, actual.id ?: "unknown") }
            .map { map(actual, form, it) }
            .switchIfEmpty(Mono.fromCallable { map(actual, form, null) })
    }

    private fun map(actual: JobApplication, form: JobApplicationUpdateForm, banned: Boolean?) = with(form) {
        JobApplication(
            actual.id,
            actual.offerId,
            actual.candidateId,
            cvFileId ?: actual.cvFileId,
            messageToRecruiter ?: actual.messageToRecruiter,
            banned ?: actual.banned,
            actual.dateCreated
        )
    }

    override fun mapToDto(item: JobApplication): Mono<JobApplicationDto> = with(item) {
        Mono.just(JobApplicationDto(id, offerId, candidateId, cvFileId, messageToRecruiter, dateCreated))
    }

    override fun mapToUpdateForm(item: JobApplication): Mono<JobApplicationUpdateForm> = with(item) {
        Mono.just(JobApplicationUpdateForm(offerId, cvFileId, messageToRecruiter))
    }
}
