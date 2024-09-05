package krystian.kryszczak.recruitment.model

interface Dto<T : Item, S : Dto<T, S>> {
    interface Mapper<T, S> {
        fun from(item: T): S
    }
}
