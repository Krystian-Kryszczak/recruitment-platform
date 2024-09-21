package krystian.kryszczak.recruitment.service.exhibit

import krystian.kryszczak.recruitment.model.UpdateForm
import krystian.kryszczak.recruitment.model.exhibit.Exhibit
import krystian.kryszczak.recruitment.service.RestrictableDataAccessService

interface ExhibitService<E : Exhibit<E>, U : UpdateForm<E, U>, ID> : RestrictableDataAccessService<E, U, ID>
