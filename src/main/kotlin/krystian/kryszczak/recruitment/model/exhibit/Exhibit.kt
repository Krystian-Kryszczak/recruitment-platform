package krystian.kryszczak.recruitment.model.exhibit

import krystian.kryszczak.recruitment.model.RestrictableItem

abstract class Exhibit<T : Exhibit<T>>(id: String?) : RestrictableItem<T>(id)
