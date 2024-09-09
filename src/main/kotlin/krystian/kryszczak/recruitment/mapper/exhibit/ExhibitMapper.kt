package krystian.kryszczak.recruitment.mapper.exhibit

import krystian.kryszczak.recruitment.mapper.ItemMapper
import krystian.kryszczak.recruitment.model.exhibit.Exhibit
import krystian.kryszczak.recruitment.model.exhibit.ExhibitCreationForm
import krystian.kryszczak.recruitment.model.exhibit.ExhibitDto
import krystian.kryszczak.recruitment.model.exhibit.ExhibitUpdateForm

interface ExhibitMapper<T : Exhibit, S : ExhibitDto<T, S>, V : ExhibitCreationForm<T, V>, U : ExhibitUpdateForm<T, U>> :
    ItemMapper<T, S, V, U, String>
