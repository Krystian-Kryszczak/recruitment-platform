package krystian.kryszczak.recruitment.mapper.being.employer

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import krystian.kryszczak.recruitment.mapper.being.BeingMapperTest
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.model.being.employer.EmployerCreationForm
import krystian.kryszczak.recruitment.model.being.employer.EmployerDto
import krystian.kryszczak.recruitment.model.being.employer.EmployerUpdateForm

@MicronautTest(transactional = false)
class EmployerMapperTest(employerMapper: EmployerMapper) //:
//BeingMapperTest<Employer, EmployerDto, EmployerCreationForm, EmployerUpdateForm>(
//    employerMapper,
//    Triple(),
//    Triple(),
//    Pair(),
//    Pair()
//) // TODO mock
