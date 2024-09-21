package krystian.kryszczak.recruitment.service.exhibit.job

import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.mapper.ItemMapper
import krystian.kryszczak.recruitment.model.exhibit.Exhibit
import krystian.kryszczak.recruitment.model.exhibit.ExhibitCreationForm
import krystian.kryszczak.recruitment.model.exhibit.ExhibitDto
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm
import krystian.kryszczak.recruitment.service.AbstractRestrictableDataAccessService

abstract class ExhibitServiceBase<T : Exhibit<T>, S : ExhibitDto<T, S>, V : ExhibitCreationForm<T, V>, U : ExhibitUpdateForm<T, U>, ID>(
    repository: CrudRepositoryBase<T, ID>,
    mapper: ItemMapper<T, S, V, U, ID>
) : AbstractRestrictableDataAccessService<T, S, V, U, ID>(repository, mapper)
