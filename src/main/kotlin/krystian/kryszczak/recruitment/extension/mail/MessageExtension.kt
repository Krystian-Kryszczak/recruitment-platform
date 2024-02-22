package krystian.kryszczak.recruitment.extension.mail

import jakarta.mail.BodyPart
import jakarta.mail.Folder
import jakarta.mail.Message
import jakarta.mail.internet.MimeMultipart
import org.jsoup.Jsoup

fun Message.readContent(): String {
    if (!folder.isOpen) {
        folder.use {
            folder.open(Folder.READ_ONLY)
            return getTextFromMessage(this)
        }
    } else {
        return getTextFromMessage(this)
    }
}

private fun getTextFromMessage(message: Message): String {
    if (message.isMimeType("text/plain")) {
        return message.content.toString()
    }
    if (message.isMimeType("multipart/*")) {
        val mimeMultipart = message.content as MimeMultipart
        return getTextFromMimeMultipart(mimeMultipart)
    }
    return ""
}

private fun getTextFromMimeMultipart(mimeMultipart: MimeMultipart): String {
    var result = ""
    for (i in 0 until mimeMultipart.getCount()) {
        val bodyPart = mimeMultipart.getBodyPart(i)
        if (bodyPart.isMimeType("text/plain")) {
            return """
                $result
                ${bodyPart.content}
                """.trim()
        }
        result += parseBodyPart(bodyPart)
    }
    return result
}

private fun parseBodyPart(bodyPart: BodyPart): String {
    if (bodyPart.isMimeType("text/html")) {
        return "\n" + Jsoup
            .parse(bodyPart.content.toString())
            .text()
    }
    return if (bodyPart.content is MimeMultipart) {
        getTextFromMimeMultipart(bodyPart.content as MimeMultipart)
    } else ""
}
