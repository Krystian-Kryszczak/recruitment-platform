package krystian.kryszczak.recruitment.model

abstract class RestrictableItem<T : RestrictableItem<T>>(id: String?) : Item(id) {
    abstract fun isBanned(): Boolean
    abstract fun isNotBanned(): Boolean
    abstract fun copyBanned(): T
    abstract fun copyUnbanned(): T
    abstract fun filterTest(): Boolean
}
