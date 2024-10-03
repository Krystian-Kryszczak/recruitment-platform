package krystian.kryszczak.recruitment.mapper.exhibit.job.offer

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferDto
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm
import krystian.kryszczak.recruitment.service.management.moderation.ModerationService
import krystian.kryszczak.recruitment.service.path.job.offer.JobOfferPathService
import krystian.kryszczak.recruitment.service.pricing.PricingService
import org.bson.types.ObjectId
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant

@Singleton
class DefaultJobOfferMapper(
    private val jobOfferPathService: JobOfferPathService,
    private val pricingService: PricingService,
    private val moderationService: ModerationService
) : JobOfferMapper {
    override fun mapToOriginItem(form: JobOfferCreationForm, cascadeId: String?): Mono<JobOffer> = with(form) {
        pricingService.findById(tierId).filter { cascadeId != null }.flatMap { tier ->
            ObjectId.get().toHexString().let { id ->
                jobOfferPathService.generatePath(id, title, id).flatMap { path ->
                    moderationService.checkIfInputIsHarmful(extractPureTextContent().toString().trim(), id)
                        .map {
                            JobOffer(
                                id,
                                tierId,
                                title,
                                cascadeId!!,
                                description,
                                mainTechnology,
                                typeOfWork,
                                experience,
                                employmentType,
                                minEarningsPerMonth,
                                maxEarningsPerMonth,
                                currency,
                                techStack,
                                locations,
                                recruitmentType,
                                operatingMode,
                                Instant.now().plus(Duration.ofDays(tier.numberOfPublicationDays.toLong())),
                                setOf(path),
                                it
                            )
                        }
                }
            }
        }
    }

    override fun mapToOriginItem(actual: JobOffer, form: JobOfferUpdateForm): Mono<JobOffer> = with(form) {
        Mono.justOrEmpty<String>(actual.id)
            .flatMap { id -> jobOfferPathService.generatePath(id, title ?: actual.title, actual.employerId).map(id::to) }
            .flatMap { (id, newPath) ->
                moderationService.checkIfInputIsHarmful(extractPureTextContent(actual).toString().trim(), id)
                    .map { banned ->
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
                            locations ?: actual.locations,
                            recruitmentType ?: actual.recruitmentType,
                            operatingMode ?: actual.operatingMode,
                            actual.expires,
                            setOf(newPath) + actual.path,
                            banned
                        )
                    }
            }
    }

    override fun mapToUpdateForm(item: JobOffer): JobOfferUpdateForm = with(item) {
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
            locations,
            recruitmentType,
            operatingMode
        )
    }

    override fun mapToDto(item: JobOffer): JobOfferDto = with(item) {
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
            locations,
            recruitmentType,
            operatingMode,
            expires,
            path,
            dateCreated
        )
    }
}
