package krystian.kryszczak.recruitment.repository.being.employer

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.being.employer.Employer
import krystian.kryszczak.recruitment.repository.being.BeingRepository

@MongoRepository
interface EmployerRepository : BeingRepository<Employer>
