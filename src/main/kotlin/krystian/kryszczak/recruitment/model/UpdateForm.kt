package krystian.kryszczak.recruitment.model

/**
 * If any variable has a null value, it should not be changed.
 */
interface UpdateForm<T : Item, S : UpdateForm<T, S>> : Form<T>  {
    /**
     * Extract text from string and iterable (arrays, lists, maps) fields.
     * Does not include file content!
     * @param actual or else data provider (evans (?:))
     * */
    fun extractPureTextContent(actual: T): StringBuilder
}
