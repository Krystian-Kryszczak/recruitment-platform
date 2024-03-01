package krystian.kryszczak.recruitment.repository.job.offer

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.constant.EmploymentType
import krystian.kryszczak.recruitment.model.constant.Experience
import krystian.kryszczak.recruitment.model.constant.RecruitmentType
import krystian.kryszczak.recruitment.model.constant.TypeOfWork
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest
import java.time.Instant

@MicronautTest(transactional = false)
class JobOfferRepositoryTest(repository: JobOfferRepository) : CrudRepositoryBaseTest<JobOffer>(
repository,
arrayOf(
    JobOffer(
        null,
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
        false,
        Instant.now()
    ), JobOffer(
        null,
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
        false,
        Instant.now()
    ), JobOffer(
        null,
        "Kotlin Developer",
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
        false,
        Instant.now()
    ), JobOffer(
        null,
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
        false,
        Instant.now()
    )
), { it.copy(title = "${it.title} - ${it.title}") },
{ item, id -> item.copy(id = id) })
