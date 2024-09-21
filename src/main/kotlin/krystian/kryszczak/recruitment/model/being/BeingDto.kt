package krystian.kryszczak.recruitment.model.being

import krystian.kryszczak.recruitment.model.Dto

interface BeingDto<T : Being<T>, S : BeingDto<T, S>> : Dto<T, S>
