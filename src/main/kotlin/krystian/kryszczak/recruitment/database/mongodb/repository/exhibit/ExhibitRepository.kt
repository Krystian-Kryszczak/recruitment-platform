package krystian.kryszczak.recruitment.database.mongodb.repository.exhibit

import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.model.exhibit.Exhibit

interface ExhibitRepository<T : Exhibit<T>> : CrudRepositoryBase<T, String>
