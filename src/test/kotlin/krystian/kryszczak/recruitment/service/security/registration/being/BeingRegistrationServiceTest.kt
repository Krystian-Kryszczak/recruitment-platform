package krystian.kryszczak.recruitment.service.security.registration.being

import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.database.mongodb.repository.security.code.activation.being.BeingActivationRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.security.credentials.being.BeingCredentialsRepository
import krystian.kryszczak.recruitment.mapper.being.BeingMapper
import krystian.kryszczak.recruitment.model.being.Being
import krystian.kryszczak.recruitment.model.being.BeingCreationForm
import krystian.kryszczak.recruitment.model.being.BeingDto
import krystian.kryszczak.recruitment.model.being.BeingUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.BeingActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.BeingCredentials
import krystian.kryszczak.recruitment.service.being.BeingService
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpMailerService
import krystian.kryszczak.recruitment.service.security.registration.RegistrationServiceTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class BeingRegistrationServiceTest<T : Being<T>, S : BeingCreationForm<T, S>, U : BeingUpdateForm<T, U>,
D : BeingDto<T, D>, C : BeingCredentials, A : BeingActivation<T, S, C>>(
    registrationService: BeingRegistrationService<T, S, U, D, C, A>,
    registerGiven: List<Pair<S, Boolean>>,
    completeActivationGiven: List<Triple<String, String, Boolean>>,
    body: FreeSpec.() -> Unit = {}
) : RegistrationServiceTest<T, S>(registrationService, registerGiven, completeActivationGiven, body) {
    protected inline fun <reified B : A, reified R : BeingActivationRepository<B>> beingActivationRepository(
        crossinline activation: (identity: String, code: String) -> B
    ): R {
        return mockk {
            every { save(any()) } answers { Mono.just(firstArg()) }
            every { delete(any()) } returns Mono.just(Long.MAX_VALUE)
            every { findByIdentityAndCode(any(), "<code>") } answers { Flux.just(activation(firstArg(), secondArg())) }
            every { findByIdentityAndCode(any(), "<wrong-code>") } returns Flux.empty()
        }
    }

    protected inline fun <reified B : C, reified R : BeingCredentialsRepository<B>> beingCredentialsRepository(): R {
        return mockk {
            every { save(any()) } answers { Mono.just(firstArg()) }
        }
    }

    protected inline fun <reified B : T, reified R : BeingService<B, S, U>> beingService(): R {
        return mockk {
            every { save(any()) } answers { Mono.just(firstArg()) }
        }
    }

    protected inline fun <reified F : S, reified B : A, reified R : BeingMapper<T, D, F, U, C, B>> beingMapper(item: T, crossinline activation: (identity: String, code: String) -> B, credentials: C): R {
        return mockk {
            every { mapToRegisterActivation(any()) } answers { Mono.just(activation(firstArg<F>().email, "<code>")) }
            every { mapToOriginItem(any()) } returns Mono.just(item)
            every { mapToCredentials(any()) } returns credentials
        }
    }

    protected open fun smtpMailerService(): SmtpMailerService {
        return mockk {
            every { sendActivationCode(any(), any()) } returns Unit
        }
    }
}
