package krystian.kryszczak.recruitment.service.text

import io.micronaut.http.MediaType
import io.micronaut.http.MediaType.*
import jakarta.inject.Singleton
import org.apache.pdfbox.Loader
import org.apache.pdfbox.io.RandomAccessReadBuffer
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import reactor.core.publisher.Mono
import java.io.File
import java.io.InputStream

@Singleton
class DefaultTextExtractionService : TextExtractionService {
    override fun extractText(file: File): Mono<String> = when(forFilename(file.name)) {
        TEXT_PLAIN_TYPE, TEXT_HTML_TYPE, TEXT_JSON_TYPE,
        TEXT_CSV_TYPE, TEXT_XML_TYPE, TEXT_EVENT_STREAM_TYPE -> extractTextFromPlain(file)
        APPLICATION_PDF_TYPE -> extractTextFromPdf(file)
        else -> extractTextFromPlain(file)
    }

    override fun extractText(inputStream: InputStream, mediaType: MediaType): Mono<String> = when(mediaType) {
        TEXT_PLAIN_TYPE, TEXT_HTML_TYPE, TEXT_JSON_TYPE,
        TEXT_CSV_TYPE, TEXT_XML_TYPE, TEXT_EVENT_STREAM_TYPE -> extractTextFromPlain(inputStream)
        APPLICATION_PDF_TYPE -> extractTextFromPdf(inputStream)
        else -> extractTextFromPlain(inputStream)
    }

    override fun extractText(bytes: ByteArray, mediaType: MediaType): Mono<String> = when(mediaType) {
        TEXT_PLAIN_TYPE, TEXT_HTML_TYPE, TEXT_JSON_TYPE,
        TEXT_CSV_TYPE, TEXT_XML_TYPE, TEXT_EVENT_STREAM_TYPE -> extractTextFromPlain(bytes)
        APPLICATION_PDF_TYPE -> extractTextFromPdf(bytes)
        else -> extractTextFromPlain(bytes)
    }

    private fun extractTextFromPlain(file: File) = Mono.fromCallable(file::readText)
    private fun extractTextFromPlain(inputStream: InputStream) = Mono.fromCallable(inputStream::readAllBytes).map(::String)
    private fun extractTextFromPlain(bytes: ByteArray) = Mono.just(bytes).map(::String)

    private fun extractTextFromPdf(file: File) = extractTextFromPdf(Loader.loadPDF(file))
    private fun extractTextFromPdf(inputStream: InputStream) = extractTextFromPdf(Loader.loadPDF(RandomAccessReadBuffer(inputStream)))
    private fun extractTextFromPdf(bytes: ByteArray) = extractTextFromPdf(Loader.loadPDF(bytes))

    private fun extractTextFromPdf(pdDocument: PDDocument) = Mono.fromCallable {
        pdDocument.use(PDFTextStripper()::getText)
    }
}
