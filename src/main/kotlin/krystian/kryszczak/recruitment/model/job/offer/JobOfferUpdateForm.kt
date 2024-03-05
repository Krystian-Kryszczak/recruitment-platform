package krystian.kryszczak.recruitment.model.job.offer

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import krystian.kryszczak.recruitment.model.UpdateForm
import krystian.kryszczak.recruitment.model.constant.EmploymentType
import krystian.kryszczak.recruitment.model.constant.Experience
import krystian.kryszczak.recruitment.model.constant.RecruitmentType
import krystian.kryszczak.recruitment.model.constant.TypeOfWork
import java.time.Instant

@Serdeable
@Introspected
data class JobOfferUpdateForm(
    val title: String,
    val description: Map<String, String>,
    val mainTechnology: String,
    val typeOfWork: TypeOfWork,
    val experience: Experience,
    val employmentType: EmploymentType,
    val minEarningsPerMonth: Int,
    val maxEarningsPerMonth: Int,
    val currency: String,
    val techStack: Map<String, Byte>?,
    val places: Array<String>?,
    val recruitmentType: RecruitmentType,
    val remote: Boolean
) : UpdateForm<JobOffer, JobOfferUpdateForm> {
    override fun transform(id: String, metadata: Map<String, Any>) = JobOffer(
        id,
        title,
        metadata.getValue("employerId") as String,
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
        remote,
        metadata.getValue("expires") as Instant,
        "" // TODO
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobOfferUpdateForm

        if (title != other.title) return false
        if (description != other.description) return false
        if (mainTechnology != other.mainTechnology) return false
        if (typeOfWork != other.typeOfWork) return false
        if (experience != other.experience) return false
        if (employmentType != other.employmentType) return false
        if (minEarningsPerMonth != other.minEarningsPerMonth) return false
        if (maxEarningsPerMonth != other.maxEarningsPerMonth) return false
        if (currency != other.currency) return false
        if (techStack != other.techStack) return false
        if (places != null) {
            if (other.places == null) return false
            if (!places.contentEquals(other.places)) return false
        } else if (other.places != null) return false
        if (recruitmentType != other.recruitmentType) return false
        if (remote != other.remote) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + mainTechnology.hashCode()
        result = 31 * result + typeOfWork.hashCode()
        result = 31 * result + experience.hashCode()
        result = 31 * result + employmentType.hashCode()
        result = 31 * result + minEarningsPerMonth
        result = 31 * result + maxEarningsPerMonth
        result = 31 * result + currency.hashCode()
        result = 31 * result + (techStack?.hashCode() ?: 0)
        result = 31 * result + (places?.contentHashCode() ?: 0)
        result = 31 * result + recruitmentType.hashCode()
        result = 31 * result + remote.hashCode()
        return result
    }

    companion object : UpdateForm.Mapper<JobOffer, JobOfferUpdateForm> {
        override fun from(item: JobOffer) = JobOfferUpdateForm(
            item.title,
            item.description,
            item.mainTechnology,
            item.typeOfWork,
            item.experience,
            item.employmentType,
            item.minEarningsPerMonth,
            item.maxEarningsPerMonth,
            item.currency,
            item.techStack,
            item.places,
            item.recruitmentType,
            item.remote
        )
    }
}
