package krystian.kryszczak.recruitment.mapper.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.mapper.being.BeingMapperTest
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.constant.Sex
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials

@MicronautTest(transactional = false)
class CandidateMapperTest(candidateMapper: CandidateMapper) :
BeingMapperTest<Candidate, CandidateDto, CandidateCreationForm, CandidateUpdateForm, CandidateCredentials, CandidateActivation>(
    candidateMapper,
    Triple(
        CandidateCreationForm(
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            true,
            "password",
            true
        ),
        "<doer-id>",
        Candidate(
            "<doer-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            banned = false,
            agreeToEmailMarketing = true
        )
    ),
    Triple(
        Candidate(
            "<candidate-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            banned = false,
            agreeToEmailMarketing = true
        ),
        CandidateUpdateForm(
            messageToTheEmployer = "Hello, there! My name is John.",
            skills = arrayOf("Kotlin", "Java", "Microservices", "Apache Cassandra")
        ),
        Candidate(
            "<candidate-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello, there! My name is John.",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf("Kotlin", "Java", "Microservices", "Apache Cassandra"),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            banned = false,
            agreeToEmailMarketing = true
        )
    ),
    Pair(
        Candidate(
            "<candidate-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            banned = false,
            agreeToEmailMarketing = true
        ),
        CandidateDto(
            "<candidate-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE
        )
    ),
    Pair(
        Candidate(
            "<candidate-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            banned = false,
            agreeToEmailMarketing = true
        ),
        CandidateUpdateForm(
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            true
        )
    ),
    Pair(
        CandidateActivation(
            "<candidate-activation-id>",
            "<candidate-activation-code>",
            "john@smith.eu",
            CandidateCreationForm(
                "john@smith.eu",
                "John",
                "Smith",
                "123 456 789",
                "Hello there!",
                "linkedin.com",
                "github.com",
                arrayOf(),
                "Java/Kotlin Developer",
                5,
                arrayOf(),
                arrayOf(),
                mapOf(),
                arrayOf(),
                5,
                Sex.MALE,
                true,
                "password",
                true
            ),
            "<encoded-password>"
        ),
        Candidate(
            "<candidate-activation-id>",
            "john@smith.eu",
            "John",
            "Smith",
            "123 456 789",
            "Hello there!",
            "linkedin.com",
            "github.com",
            arrayOf(),
            "Java/Kotlin Developer",
            5,
            arrayOf(),
            arrayOf(),
            mapOf(),
            arrayOf(),
            5,
            Sex.MALE,
            banned = false,
            agreeToEmailMarketing = true
        )
    ),
    Pair(
        CandidateActivation(
            "<candidate-activation-id>",
            "<candidate-activation-code>",
            "john@smith.eu",
            CandidateCreationForm(
                "john@smith.eu",
                "John",
                "Smith",
                "123 456 789",
                "Hello there!",
                "linkedin.com",
                "github.com",
                arrayOf(),
                "Java/Kotlin Developer",
                5,
                arrayOf(),
                arrayOf(),
                mapOf(),
                arrayOf(),
                5,
                Sex.MALE,
                true,
                "password",
                true
            ),
            "<encoded-password>"
        ),
        CandidateCredentials(
            null,
            "john@smith.eu",
            "<encoded-password>"
        )
    ),
    { item, id -> item.copy(id = id) }
)
