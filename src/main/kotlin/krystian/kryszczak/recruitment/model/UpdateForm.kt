package krystian.kryszczak.recruitment.model

interface UpdateForm<T : Item, S : UpdateForm<T, S>> {
    fun transform(id: String, metadata: Map<String, Any> = mapOf()): T

    interface Mapper<T, S> {
        fun from(item: T): S
    }
}
