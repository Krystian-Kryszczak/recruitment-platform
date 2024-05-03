package krystian.kryszczak.recruitment.controller.api.being.candidate

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.being.BeingController
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto.Companion as mapper
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService

@RolesAllowed("CANDIDATE")
@Controller("api/v1/candidate/")
@ExecuteOn(TaskExecutors.BLOCKING)
open class CandidateController(service: CandidateService) :
    BeingController<Candidate, CandidateCreationForm, CandidateUpdateForm, CandidateDto>(service, mapper)
