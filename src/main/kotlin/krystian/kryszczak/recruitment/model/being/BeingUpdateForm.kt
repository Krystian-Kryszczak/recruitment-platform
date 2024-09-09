package krystian.kryszczak.recruitment.model.being

import krystian.kryszczak.recruitment.model.UpdateForm

/**
 * If any variable has a null value, it should not be changed.
 */
interface BeingUpdateForm<T : Being, S : BeingUpdateForm<T, S>> : UpdateForm<T, S>
