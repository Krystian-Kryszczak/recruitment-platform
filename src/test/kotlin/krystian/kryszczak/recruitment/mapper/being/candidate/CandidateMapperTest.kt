package krystian.kryszczak.recruitment.mapper.being.candidate

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.mapper.being.BeingMapperTest
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.CandidateCreationForm
import krystian.kryszczak.recruitment.model.being.candidate.CandidateDto
import krystian.kryszczak.recruitment.model.being.candidate.CandidateUpdateForm

@MicronautTest(transactional = false)
class CandidateMapperTest(candidateMapper: CandidateMapper) //:
//BeingMapperTest<Candidate, CandidateDto, CandidateCreationForm, CandidateUpdateForm>(
//    candidateMapper,
//    Triple(),
//    Triple(),
//    Pair(),
//    Pair()
//) // TODO mock
