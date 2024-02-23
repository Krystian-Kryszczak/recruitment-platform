package com.multhink.service.security.change.password

import com.multhink.model.security.code.reset.ResetPassword
import com.multhink.security.encoder.PasswordEncoder
import com.multhink.security.validation.PasswordValidator
import com.multhink.service.mailer.smtp.SmtpMailerService
import com.multhink.storage.cassandra.dao.security.credentials.UserCredentialsDao
import com.multhink.storage.cassandra.dao.security.reset.ResetPasswordDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

@Singleton
class UserChangePasswordServiceImpl(
    private val userCredentialsDao: UserCredentialsDao,
    private val resetPasswordDao: ResetPasswordDao,
    private val passwordEncoder: PasswordEncoder,
    private val smtpMailerService: SmtpMailerService,
): UserChangePasswordService {
    override fun generateChangeUserPasswordCode(id: UUID, oldPassword: String): Maybe<ResetPassword> {
        if (!PasswordValidator.validate(oldPassword)) return Maybe.empty()
        return Maybe.fromPublisher(userCredentialsDao.findById(id))
            .flatMap {
                if (passwordEncoder.matches(oldPassword, it.hashedPassword ?: return@flatMap Maybe.empty())) {
                    val resetPassword = ResetPassword.createWithGeneratedCode(id)
                    if (resetPassword.code != null) Maybe.just(resetPassword) else Maybe.empty()
                } else {
                    Maybe.empty()
                }
            }
    }

    override fun saveResetPassword(resetPassword: ResetPassword): Single<Boolean> =
        Completable.fromPublisher(resetPasswordDao.save(resetPassword))
        .toSingleDefault(true).onErrorReturnItem(false)

    override fun sendChangeUserPasswordCodeToEmail(resetPassword: ResetPassword, email: String): Single<Boolean> {
        return smtpMailerService.sendUserResetPasswordCode(email, resetPassword.code ?: return Single.just(false))
    }

    override fun changeUserPassword(id: UUID, resetPasswordCode: String, newPassword: String): Single<Boolean> {
        if (resetPasswordCode.isBlank() || !PasswordValidator.validate(newPassword)) return Single.just(false)

        return Maybe.fromPublisher(resetPasswordDao.findByIdAndCode(id, resetPasswordCode))
            .flatMapSingle {
                Completable.fromPublisher(userCredentialsDao.updatePasswordById(id, newPassword))
                    .andThen(Completable.fromFuture(resetPasswordDao.deleteByCode(resetPasswordCode)))
                    .andThen(Single.just(true))
                    .onErrorReturnItem(false)
            }.defaultIfEmpty(false)
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(UserChangePasswordServiceImpl::class.java)
    }
}
