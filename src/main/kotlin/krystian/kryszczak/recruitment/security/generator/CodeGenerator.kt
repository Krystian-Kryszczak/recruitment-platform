package krystian.kryszczak.recruitment.security.generator

interface CodeGenerator<T: Any> {
    fun generateCode(): T
}
