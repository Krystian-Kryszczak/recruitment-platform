package krystian.kryszczak.recruitment.controller.api.being.candidate

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.annotation.security.RolesAllowed
import krystian.kryszczak.recruitment.controller.api.being.BeingController
import krystian.kryszczak.recruitment.mapper.being.candidate.CandidateMapper
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm
import krystian.kryszczak.recruitment.model.security.code.activation.being.candidate.CandidateActivation
import krystian.kryszczak.recruitment.model.security.credentials.being.candidate.CandidateCredentials
import krystian.kryszczak.recruitment.service.being.candidate.CandidateService

@RolesAllowed("CANDIDATE")
@Controller("api/v1/candidate/")
@ExecuteOn(TaskExecutors.IO)
open class CandidateController(service: CandidateService, mapper: CandidateMapper) :
    BeingController<Candidate, CandidateCreationForm, CandidateUpdateForm, CandidateDto, CandidateCredentials, CandidateActivation>(service, mapper)
