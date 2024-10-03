package krystian.kryszczak.recruitment.repository.job.offer

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.database.mongodb.repository.exhibit.job.offer.JobOfferRepository
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest
import krystian.kryszczak.test.mock.tierMock
import java.time.Instant

@MicronautTest(transactional = false)
class JobOfferRepositoryTest(repository: JobOfferRepository) : CrudRepositoryBaseTest<JobOffer>(
repository,
arrayOf(
    JobOffer(
        null,
        tierMock.id!!,
        "Senior Java Software Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Java",
        TypeOfWork.FULL_TIME,
        Experience.SENIOR,
        EmploymentType.PERMANENT,
        18000,
        22000,
        "PLN",
        mapOf("Java" to 4, "Microservices" to 4, "NoSQL" to 3, "Cloud" to 1),
        arrayOf("Warsaw"),
        RecruitmentType.ONLINE_INTERVIEW,
        OperatingMode.ON_SITE,
        Instant.now(),
        setOf("senior-java-software-developer"),
        false
    ), JobOffer(
        null,
        tierMock.id!!,
        "Java Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Java",
        TypeOfWork.FULL_TIME,
        Experience.MID,
        EmploymentType.B2B,
        18000,
        24000,
        "PLN",
        mapOf("Java" to 4, "Micronaut" to 4, "Apache Kafka" to 3, "Elasticsearch" to 3, "Microservices" to 4),
        arrayOf("Warsaw"),
        RecruitmentType.ONLINE_INTERVIEW,
        OperatingMode.ON_SITE,
        Instant.now(),
        setOf("java-developer"),
        false
    ), JobOffer(
        null,
        tierMock.id!!,
        "Senior Kotlin Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Kotlin",
        TypeOfWork.FULL_TIME,
        Experience.MID,
        EmploymentType.B2B,
        18000,
        22000,
        "PLN",
        mapOf("Kotlin" to 4, "Micronaut" to 4, "Apache Kafka" to 3, "Elasticsearch" to 3, "Microservices" to 4),
        arrayOf("Warsaw"),
        RecruitmentType.ONLINE_INTERVIEW,
        OperatingMode.HYBRID,
        Instant.now(),
        setOf("senior-kotlin-software-developer"),
        false
    ), JobOffer(
        null,
        tierMock.id!!,
        "Java Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Java",
        TypeOfWork.FULL_TIME,
        Experience.SENIOR,
        EmploymentType.B2B,
        16000,
        28000,
        "PLN",
        mapOf("Java" to 4, "CI/CD" to 4, "Docker" to 3, "Kubernetes" to 3, "Microservice Architecture" to 3),
        arrayOf("Warsaw"),
        RecruitmentType.ONLINE_INTERVIEW,
        OperatingMode.REMOTE,
        Instant.now(),
        setOf("mid-java-developer"),
        false
    )
), { it.copy(title = "${it.title} - ${it.title}") },
{ item, id -> item.copy(id = id) },
{ givenItems ->
    "job offer repository test" - {
        "exits by path" {
            // given
            val paths = arrayOf(
                "senior-java-software-developer",
                "senior-kotlin-software-developer"
            )
            val jobOffers = givenItems.filter { item -> paths.any { item.path.contains(it) } }

            // prepare
            repository.saveAll(jobOffers)
                .blockLast()
                .shouldNotBeNull()

            // given
            val nonExitsPaths = arrayOf(
                "not-exits-kotlin-software-developer-job-offer",
                "not-exits-java-software-developer-job-offer"
            )
            val expectations = paths.associateWith { true } +
                nonExitsPaths.associateWith { false }

            for ((path, excepted) in expectations) {
                // when
                val result = repository.existsByPathArrayContains(path)
                    .block()

                // then
                result.shouldNotBeNull()
                    .shouldBe(excepted)
            }
        }
    }
})
