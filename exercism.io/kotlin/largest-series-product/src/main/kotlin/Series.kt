class Series(val str: String) {
    init {
        require(str.all { it - '0' in 0..9 })
    }

    fun getLargestProduct(len: Int) : Long = when {
        str.length < len || len < 0 -> throw IllegalArgumentException()
        else -> (0 .. str.length - len).map {
            (it .. it + len - 1).fold(1L) { l, r -> l * (str[r] - '0') }
        }.max() ?: 1
    }
}
