package krystian.kryszczak.recruitment.service.mail.smtp

import io.micronaut.email.MultipartBody

interface SmtpMailerService {
    fun send(to: String, subject: String, content: String)
    fun send(to: String, subject: String, content: MultipartBody)
    fun sendUsingTemplate(to: String, subject: String, templateName: String, model: Map<String, String>)
    fun sendUserActivationCode(to: String, activateCode: String)
    fun sendResetPasswordCode(to: String, resetPasswordCode: String)
    fun sendNewVideoNotification(to: String, author: String, avatarUrl: String, title: String, videoUrl: String)
}
