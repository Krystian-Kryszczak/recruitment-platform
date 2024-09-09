package krystian.kryszczak.recruitment.mapper.exhibit.job.application

import krystian.kryszczak.recruitment.mapper.exhibit.ExhibitMapper
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplication
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationCreationForm
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationDto
import krystian.kryszczak.recruitment.model.exhibit.job.application.JobApplicationUpdateForm

interface JobApplicationMapper : ExhibitMapper<JobApplication, JobApplicationDto, JobApplicationCreationForm, JobApplicationUpdateForm>
