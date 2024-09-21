package krystian.kryszczak.recruitment.mapper.exhibit.job.application

import io.micronaut.http.MediaType.TEXT_PLAIN_TYPE
import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.extension.upload.isJobApplicationAttachmentAcceptable
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm
import krystian.kryszczak.recruitment.model.upload.FileUploadOrReferenceId
import krystian.kryszczak.recruitment.service.blob.BlobStorageService
import krystian.kryszczak.recruitment.service.management.moderation.ModerationService
import krystian.kryszczak.recruitment.service.text.TextExtractionService
import org.bson.types.ObjectId
import reactor.core.publisher.Mono

@Singleton
class DefaultJobApplicationMapper(
    private val blobStorageService: BlobStorageService,
    private val textExtractionService: TextExtractionService,
    private val moderationService: ModerationService
) : JobApplicationMapper {
    override fun mapToOriginItem(form: JobApplicationCreationForm, cascadeId: String?): Mono<JobApplication> = with(form) {
        ObjectId.get().toHexString().let { id ->
            cvFile.use(
                onUpload = {
                    textExtractionService.extractText(asInputStream(), contentType.orElse(TEXT_PLAIN_TYPE))
                        .map { extractPureTextContent().append(" ").append(it).toString().trim() }
                },
                onReferenceId = { Mono.just(extractPureTextContent().toString().trim()) }
            ).flatMap { moderationService.checkIfInputIsHarmful(it, id) }
                .flatMap { map(id, form, cascadeId, it) }
                .switchIfEmpty(map(id, form, cascadeId, false))
        }
    }

    private fun map(id: String, form: JobApplicationCreationForm, doerId: String?, banned: Boolean): Mono<JobApplication> = with(form) {
        cvFile.use(
            onUpload = {
                Mono.just(this)
                    .flatMap {
                        if (it.isJobApplicationAttachmentAcceptable()) {
                            blobStorageService.save(id, Mono.just(it))
                        } else {
                            Mono.just("")
                        }
                    }.map {
                        JobApplication(
                            id,
                            offerId,
                            doerId,
                            firstName,
                            lastName,
                            email,
                            messageToRecruiter,
                            null,
                            banned,
                            null
                        )
                    }
            },
            onReferenceId = {
                Mono.just(
                    JobApplication(
                        id,
                        offerId,
                        doerId,
                        firstName,
                        lastName,
                        email,
                        messageToRecruiter,
                        this,
                        banned,
                        null
                    )
                )
            }
        )
    }

    override fun mapToOriginItem(actual: JobApplication, form: JobApplicationUpdateForm): Mono<JobApplication> = with(form) {
        (cvFile?.use(
            onUpload = {
                textExtractionService.extractText(asInputStream(), contentType.orElse(TEXT_PLAIN_TYPE))
            },
            onReferenceId = {
                blobStorageService.getById(this)
                    .flatMap { textExtractionService.extractText(it.inputStream, it.mediaType) }
                    .single()
            }
        ) ?: Mono.just(""))
        .map { extractPureTextContent(actual).append(" ").append(it).toString().trim() }
        .flatMap { moderationService.checkIfInputIsHarmful(it, actual.id ?: "unknown") }
        .map { map(actual, form, it) }
    }

    private fun map(actual: JobApplication, form: JobApplicationUpdateForm, banned: Boolean) = with(form) {
        JobApplication(
            actual.id,
            actual.offerId,
            actual.candidateId,
            firstName ?: actual.firstName,
            lastName ?: actual.lastName,
            email ?: actual.email,
            messageToRecruiter ?: actual.messageToRecruiter,
            cvFile?.referenceIdOrNull(),
            banned,
            actual.dateCreated
        )
    }

    override fun mapToDto(item: JobApplication): JobApplicationDto = with(item) {
        JobApplicationDto(id, offerId, candidateId, firstName, lastName, email, messageToRecruiter, dateCreated)
    }

    override fun mapToUpdateForm(item: JobApplication): JobApplicationUpdateForm = with(item) {
        JobApplicationUpdateForm(firstName, lastName, email, cvFileReferenceId?.let(::FileUploadOrReferenceId), messageToRecruiter)
    }
}
