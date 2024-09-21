package krystian.kryszczak.recruitment.extension.upload

import io.micronaut.http.MediaType.*
import io.micronaut.http.multipart.StreamingFileUpload
import kotlin.jvm.optionals.getOrNull

fun StreamingFileUpload.isJobApplicationAttachmentAcceptable() = contentType.getOrNull()
    ?.let { it == APPLICATION_PDF_TYPE || it == TEXT_PLAIN_TYPE } == true
