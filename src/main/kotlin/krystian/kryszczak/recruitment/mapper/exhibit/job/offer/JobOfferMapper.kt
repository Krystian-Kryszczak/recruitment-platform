package krystian.kryszczak.recruitment.mapper.exhibit.job.offer

import krystian.kryszczak.recruitment.mapper.exhibit.ExhibitMapper
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOffer
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferDto
import krystian.kryszczak.recruitment.model.exhibit.job.offer.JobOfferUpdateForm

interface JobOfferMapper : ExhibitMapper<JobOffer, JobOfferDto, JobOfferCreationForm, JobOfferUpdateForm>
