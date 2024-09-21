package krystian.kryszczak.recruitment.model.exhibit

import krystian.kryszczak.recruitment.model.UpdateForm

interface ExhibitUpdateForm<T : Exhibit<T>, S : ExhibitUpdateForm<T, S>> : UpdateForm<T, S>
