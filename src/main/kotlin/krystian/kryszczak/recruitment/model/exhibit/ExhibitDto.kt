package krystian.kryszczak.recruitment.model.exhibit

import krystian.kryszczak.recruitment.model.Dto

interface ExhibitDto<T : Exhibit, S : ExhibitDto<T, S>> : Dto<T, S>
