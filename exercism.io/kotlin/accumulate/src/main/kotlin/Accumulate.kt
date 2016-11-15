object Accumulate {
    fun <T> accumulate(input: List<T>, p: (T) -> Any) : List<*> = input
            .map { p(it) }
}