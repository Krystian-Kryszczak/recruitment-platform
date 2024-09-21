package krystian.kryszczak.recruitment.service.management.verification

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.Call
import io.mockk.every
import io.mockk.mockk
import krystian.kryszczak.recruitment.service.RestrictableDataAccessService
import reactor.core.publisher.Mono

abstract class VerificationServiceTest<T : Any>(
    verificationService: VerificationService<T, String>,
    item: T,
    copyFun: T.(verified: Boolean) -> T
) : FreeSpec({
    "verification service test" - {
        "verify" {
            // when
            val result = verificationService.verifyById("<some-id>")
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(copyFun(item, true))
        }

        "unverify" {
            // when
            val result = verificationService.unverifyById("<some-id>")
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(copyFun(item, false))
        }

        "is verified" {
            // when
            val result = verificationService.isVerifiedById("<some-id>")
                .block()

            // then
            result.shouldNotBeNull()
                .shouldBe(false)
        }
    }
}) {
    protected inline fun <reified S : T, reified R : RestrictableDataAccessService<S, *, String>> dataAccessService(result: S): R {
        return mockk<R> {
            every { findById(any()) } returns Mono.just(result)
            every { update(any<S>()) } coAnswers {
                call: Call -> Mono.just(call.invocation.args.first() as S)
            }
        }
    }
}
