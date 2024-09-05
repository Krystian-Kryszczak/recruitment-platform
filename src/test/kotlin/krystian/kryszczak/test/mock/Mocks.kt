package krystian.kryszczak.test.mock

import io.kotest.mpp.uniqueId
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.constant.*
import krystian.kryszczak.recruitment.model.job.application.JobApplication
import krystian.kryszczak.recruitment.model.job.offer.JobOffer
import java.time.Duration
import java.time.Instant

val candidateMock = Candidate(
    uniqueId(),
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
    Sex.MALE,
    false,
    true
)

val employerMock = Employer(
    uniqueId(),
    "E-Corp",
    "Hello world!",
    "Conglomerate",
    "FinTech",
    5,
    "https://mrrobot.fandom.com/wiki/E-Corp",
    "facebook.com",
    "instagram.com",
    "linkedin.com",
    "contact@e-corp.com",
    arrayOf(),
    arrayOf(),
    false,
    true
)

val jobOfferMock = JobOffer(
    uniqueId(),
    "java-senior-developer",
    employerMock.id!!,
    mapOf(),
    "Java",
    TypeOfWork.FULL_TIME,
    Experience.MID,
    EmploymentType.B2B,
    18000,
    22000,
    "PLN",
    mapOf("Java" to 5, "Micronaut" to 4, "Microservices" to 4),
    arrayOf("Warsaw"),
    RecruitmentType.ONLINE_INTERVIEW,
    true,
    Instant.now().plus(Duration.ofDays(30)),
    "e-corp-java-senior-developer"
)

val jobApplicationMock = JobApplication(
    uniqueId(),
    jobOfferMock.id!!,
    candidateMock.id!!,
    uniqueId(),
    "Hello there!",
    null,
    false,
    Instant.now()
)
