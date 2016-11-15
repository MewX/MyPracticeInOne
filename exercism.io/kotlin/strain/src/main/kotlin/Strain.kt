object Strain {
    fun <T> keep(n: List<T>, p: (T) -> Boolean) : List<T> = n.fold(listOf<T>()) {
        sum, it -> if (p(it)) sum + it else sum
    }

    fun <T> discard(n: List<T>, p: (T) -> Boolean) : List<T> = keep(n) { !p(it) }
}