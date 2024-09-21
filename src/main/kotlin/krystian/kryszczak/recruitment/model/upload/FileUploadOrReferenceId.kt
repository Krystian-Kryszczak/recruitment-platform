package krystian.kryszczak.recruitment.model.upload

import io.micronaut.http.multipart.StreamingFileUpload

class FileUploadOrReferenceId {
    private val upload: StreamingFileUpload?
    private val referenceId: String?

    constructor(upload: StreamingFileUpload) {
        this.upload = upload
        this.referenceId = null
    }

    constructor(referenceId: String) {
        this.upload = null
        this.referenceId = referenceId
    }

    fun <R> use(onUpload: StreamingFileUpload.() -> R, onReferenceId: String.() -> R): R =
        if (upload != null) onUpload(upload) else onReferenceId(referenceId!!)

    fun referenceIdOrNull() = referenceId
}
