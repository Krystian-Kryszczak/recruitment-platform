package krystian.kryszczak.recruitment.model.exhibit

import krystian.kryszczak.recruitment.model.CreationForm

interface ExhibitCreationForm<T : Exhibit, S : ExhibitCreationForm<T, S>> : CreationForm<T, S>
