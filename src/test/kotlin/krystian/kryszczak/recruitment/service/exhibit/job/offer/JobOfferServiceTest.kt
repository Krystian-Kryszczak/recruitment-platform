package krystian.kryszczak.recruitment.service.exhibit.job.offer

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferQuery
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.exhibit.ExhibitServiceTest
import krystian.kryszczak.test.mock.employerMock
import java.time.Duration
import java.time.Instant

@MicronautTest(transactional = false)
class JobOfferServiceTest(jobOfferService: JobOfferService) :
ExhibitServiceTest<JobOffer, JobOfferUpdateForm>(
jobOfferService,
arrayOf(
    JobOffer(
        null,
        uniqueId(),
        "Kotlin Senior Developer",
        employerMock.id!!,
        mapOf(),
        "Kotlin",
        TypeOfWork.FULL_TIME,
        Experience.SENIOR,
        EmploymentType.B2B,
        20000,
        22000,
        "PLN",
        mapOf("Kotlin" to 5, "Java" to 5, "Micronaut" to 4, "Microservices" to 4),
        arrayOf("London"),
        RecruitmentType.ONLINE_INTERVIEW,
        OperatingMode.REMOTE,
        Instant.now().plus(Duration.ofDays(30)),
        setOf("e-corp-kotlin-senior-developer"),
        false,
        Instant.now()
    ), JobOffer(
        null,
        uniqueId(),
        "Java Senior Developer",
        employerMock.id!!,
        mapOf(),
        "Java",
        TypeOfWork.FULL_TIME,
        Experience.SENIOR,
        EmploymentType.B2B,
        18000,
        22000,
        "PLN",
        mapOf("Java" to 5, "Micronaut" to 4, "Microservices" to 4),
        arrayOf("Warsaw"),
        RecruitmentType.ONLINE_INTERVIEW,
        OperatingMode.REMOTE,
        Instant.now().plus(Duration.ofDays(30)),
        setOf("e-corp-java-senior-developer"),
        false,
        Instant.now()
    )
),
{ it.copy(title = "${it.title} - Updated") },
{ item, id -> item.copy(id = id) }, { givenItems ->
    "job offer service test" - {
        "find by path or id" {
            // given
            val given = mapOf(
                givenItems[0].path.first() to givenItems[0],
                givenItems[0].id!! to givenItems[0],
                givenItems[1].path.first() to givenItems[1],
                givenItems[1].id!! to givenItems[1],
                "none" to null,
                "nothing" to null
            )

            for ((data, excepted) in given) {
                // when
                val result = jobOfferService.findByPathOrId(data)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }

        "find by path" {
            // given
            val given = mapOf(
                givenItems[0].path.first() to givenItems[0],
                givenItems[1].path.first() to givenItems[1],
                "none" to null,
                "nothing" to null
            )

            for ((data, excepted) in given) {
                // when
                val result = jobOfferService.findByPath(data)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }

        "exists by path" {
            // given
            val given = mapOf(
                givenItems[0].path.first() to true,
                givenItems[1].path.first() to true,
                "none" to false,
                "nothing" to false
            )

            for ((data, excepted) in given) {
                // when
                val result = jobOfferService.existsByPath(data)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }

        "search" {
            // given
            val query = JobOfferQuery(
                mainTechnology = givenItems.first().mainTechnology
            )

            // when
            val result = jobOfferService.search(query)
                .toIterable()

            // then
            result.shouldNotBeNull()
                .shouldNotBeEmpty()
                .shouldContain(givenItems.first())
        }
// TODO
        "find by employer id" {
//            // given
//            val given = mapOf(
//                givenItems[0].path.first() to true,
//                givenItems[1].path.first() to true,
//                "none" to false,
//                "nothing" to false
//            )
//
//            for ((data, excepted) in given) {
//                // when
//                val result = jobOfferService.findByEmployerId(data)
//                    .block()
//
//                // then
//                result.shouldNotBeNull()
//                    .shouldBe(excepted)
//            }
        }

        "find by employer auth" {
            //
        }

        "employer add" {
            //
        }

        "employer update" {
            //
        }

        "employer remove" {
            //
        }
    }
})
