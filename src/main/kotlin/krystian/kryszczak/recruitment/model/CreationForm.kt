package krystian.kryszczak.recruitment.model

interface CreationForm<T : Item, S : CreationForm<T, S>> : Form<T>  {
    /**
     * Extract text from string and iterable (arrays, lists, maps) fields.
     * Does not include file content!
     * */
    fun extractPureTextContent(): StringBuilder
}
