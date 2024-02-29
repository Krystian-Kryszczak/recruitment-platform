package krystian.kryszczak.recruitment.model

interface CreationForm<T : Item, S : CreationForm<T, S>> {
    fun transform(metadata: Map<String, Any> = mapOf()): T
}
