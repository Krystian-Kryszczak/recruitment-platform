package krystian.kryszczak.recruitment.model

/**
 * If any variable has a null value, it should not be changed.
 */
interface UpdateForm<T : Item, S : UpdateForm<T, S>> {
    fun transform(actual: T, metadata: Map<String, Any> = mapOf()): T

    interface Mapper<T, S> {
        fun from(item: T): S
    }
}
