package krystian.kryszczak.recruitment.repository.job.offer

import io.kotest.mpp.uniqueId
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import krystian.kryszczak.recruitment.repository.CrudRepositoryBaseTest

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
        "Full-time",
        "Senior",
        "Permanent",
        "Remote",
        "18000 - 22000 PLN",
        mapOf("Java" to 4, "Microservices" to 4, "NoSQL" to 3, "Cloud" to 1),
        arrayOf("Warsaw"),
        "Online interview"
    ), JobOffer(
        null,
        "Java Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Java",
        "Full-time",
        "Mid",
        "B2B",
        "Remote",
        "18000 - 24000 PLN",
        mapOf("Java" to 4, "Micronaut" to 4, "Apache Kafka" to 3, "Elasticsearch" to 3, "Microservices" to 4),
        arrayOf("Warsaw"),
        "Online interview"
    ), JobOffer(
        null,
        "Kotlin Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Kotlin",
        "Full-time",
        "Mid",
        "B2B",
        "Remote",
        "18000 - 22000 PLN",
        mapOf("Kotlin" to 4, "Micronaut" to 4, "Apache Kafka" to 3, "Elasticsearch" to 3, "Microservices" to 4),
        arrayOf("Warsaw"),
        "Online interview"
    ), JobOffer(
        null,
        "Java Developer",
        uniqueId(),
        mapOf("Some Header" to "Hello world!"),
        "Java",
        "Full-time",
        "Senior",
        "B2B",
        "Hybrid",
        "16000 - 28000 PLN",
        mapOf("Java" to 4, "CI/CD" to 4, "Docker" to 3, "Kubernetes" to 3, "Microservice Architecture" to 3),
        arrayOf("Warsaw"),
        "Online interview"
    )
), { it.copy(title = "${it.title} - ${it.title}") },
{ item, id -> item.copy(id = id) })
