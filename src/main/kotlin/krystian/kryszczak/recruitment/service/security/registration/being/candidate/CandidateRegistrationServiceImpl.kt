package krystian.kryszczak.recruitment.service.security.registration.being.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.security.encoder.PasswordEncoder
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.metrics.MetricsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

@Singleton
class CandidateRegistrationServiceImpl(
    private val metricsService: MetricsService,
//    private val activationCodeDao: UserAccountActivationDao,
//    private val userService: UserService,
//    private val userCredentialsDao: UserCredentialsDao,
    private val passwordEncoder: PasswordEncoder,
    private val smtpMailerService: SmtpMailerService,
) : CandidateRegistrationService {
    override fun register(formation: CandidateCreationForm): Mono<Boolean> {
//        if (!PasswordValidator.validate(formation.password)) {
//            logger.info("Invalid user password! User email: ${formation.email}")
//            return Single.just(false)
//        }
//
//        if (formation.password == null) return Single.just(false)
//        val modelWithEncodedPassword = UserModel(
//            formation.firstname,
//            formation.lastname,
//            formation.email,
//            formation.phoneNumber,
//            passwordEncoder.encode(formation.password),
//            formation.dateOfBirthInDays,
//            formation.sex
//        )
//
//        return generateActivationCodeForUser(modelWithEncodedPassword)
//            .flatMap { code -> run {
//                val email: String = modelWithEncodedPassword.email ?: return@run Single.just(false)
//                UserRegistrationServiceImpl.logger.info("The activation code `$code` will be sent to the email address $email")
//                smtpMailerService.sendUserActivationCode(email, code)
//            }}.onErrorReturn {
//                it.printStackTrace()
//                return@onErrorReturn false
//            }
        return Mono.just(false)
    }

    override fun completeActivationUserAccount(email: String, code: String): Mono<Boolean> = Mono.just(false)
//        activationCodeMatches(email, code)
//            .flatMapSingle {
//                    activation -> run {
//                val user = activation.mapToUser() ?: return@run Single.just(false)
//                val userCredentials = activation.mapToUserCredentials() ?: return@run Single.just(false)
//                Completable.fromPublisher(activationCodeDao.delete(activation))
//                    .andThen(userService.save(user))
//                    .andThen(Completable.fromPublisher(userCredentialsDao.save(userCredentials)))
//                    .doOnComplete {
//                        logger.info("Successful activated account with email $email")
//                        metricsService.incrementActivatedAccounts()
//                    }.toSingleDefault(true)
//                    .onErrorReturn {
//                        metricsService.incrementActivationAccountFails()
//                        it.printStackTrace()
//                        return@onErrorReturn false
//                    }
//            }
//            }.switchIfEmpty(Single.create {
//                metricsService.incrementActivationAccountFails()
//                it.onSuccess(false)
//            }).onErrorReturn {
//                metricsService.incrementActivationAccountFails()
//                return@onErrorReturn false
//            }

//    private fun generateActivationCodeForUser(userModel: UserModel): Mono<String> {
////        val activation = UserAccountActivation.createWithGeneratedCode(
////            identity = userModel.email,
////            userModel = userModel,
////        )
////        return Completable.fromPublisher(activationCodeDao.save(activation))
////            .doOnComplete {
////                metricsService.incrementGeneratedActivationCodes()
////            }.toSingleDefault(activation.code!!)
//        return Mono.empty()
//    }

//    private fun activationCodeMatches(userEmail: String, code: String): Maybe<UserAccountActivation> =
//        Maybe.fromPublisher(activationCodeDao.findByEmailAndCode(userEmail, code))

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CandidateRegistrationServiceImpl::class.java)
    }
}
