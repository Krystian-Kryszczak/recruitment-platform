package krystian.kryszczak.recruitment.model.being

import krystian.kryszczak.recruitment.model.Dto

interface BeingDto<T : Being, S : BeingDto<T, S>> : Dto<T, S>
