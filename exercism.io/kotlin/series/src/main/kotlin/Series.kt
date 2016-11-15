object Series {
    fun slices(n: Int, str: String) : List<List<Int>> = (0 .. str.length - n)
            .map { str.substring(it, it + n).map { it - '0' } }
}