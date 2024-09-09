package krystian.kryszczak.recruitment.mapper.exhibit.job.offer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferDto
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.path.job.offer.JobOfferPathService
import krystian.kryszczak.recruitment.service.pricing.PricingService
import org.bson.types.ObjectId
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant

@Singleton
class DefaultJobOfferMapper(
    private val jobOfferPathService: JobOfferPathService,
    private val pricingService: PricingService
) : JobOfferMapper {
    override fun mapToOriginItem(form: JobOfferCreationForm, doerId: String): Mono<JobOffer> = with(form) {
        pricingService.findById(tierId).flatMap { tier ->
            ObjectId.get().toHexString().let { id ->
                jobOfferPathService.generatePath(id, title, id).map { path ->
                    JobOffer(
                        id,
                        tierId,
                        title,
                        doerId,
                        description,
                        mainTechnology,
                        typeOfWork,
                        experience,
                        employmentType,
                        minEarningsPerMonth,
                        maxEarningsPerMonth,
                        currency,
                        techStack,
                        places,
                        recruitmentType,
                        operatingMode,
                        Instant.now().plus(Duration.ofDays(tier.numberOfPublicationDays.toLong())),
                        setOf(path)
                    )
                }
            }
        }
    }

    override fun mapToOriginItem(actual: JobOffer, form: JobOfferUpdateForm): Mono<JobOffer> = with(form) {
        Mono.just( // TODO add new path when offer is updated
            JobOffer(
                actual.id,
                actual.tierId,
                title ?: actual.title,
                actual.employerId,
                description ?: actual.description,
                mainTechnology ?: actual.mainTechnology,
                typeOfWork ?: actual.typeOfWork,
                experience ?: actual.experience,
                employmentType ?: actual.employmentType,
                minEarningsPerMonth ?: actual.minEarningsPerMonth,
                maxEarningsPerMonth ?: actual.maxEarningsPerMonth,
                currency ?: actual.currency,
                techStack ?: actual.techStack,
                places ?: actual.places,
                recruitmentType ?: actual.recruitmentType,
                operatingMode ?: actual.operatingMode,
                actual.expires,
                actual.path
            )
        )
    }

    override fun mapToUpdateForm(item: JobOffer): Mono<JobOfferUpdateForm> = with(item) {
        Mono.just(
            JobOfferUpdateForm(
                title,
                description,
                mainTechnology,
                typeOfWork,
                experience,
                employmentType,
                minEarningsPerMonth,
                maxEarningsPerMonth,
                currency,
                techStack,
                places,
                recruitmentType,
                operatingMode
            )
        )
    }

    override fun mapToDto(item: JobOffer): Mono<JobOfferDto> = with(item) {
        Mono.just(
            JobOfferDto(
                id,
                title,
                employerId,
                description,
                mainTechnology,
                typeOfWork,
                experience,
                employmentType,
                minEarningsPerMonth,
                maxEarningsPerMonth,
                currency,
                techStack,
                places,
                recruitmentType,
                operatingMode,
                expires,
                path,
                dateCreated
            )
        )
    }
}
