class Luhn(n: Long) {
    val addends: List<Int> = check(n)
    val checksum: Int = addends.sum()
    val isValid: Boolean = checksum % 10 == 0
    val checkDigit: Int = n.toString().last().toInt() - '0'.toInt()
    val create: Long = (0..9).map { n * 10L + it }.filter { check(it).sum() % 10 == 0 }.getOrElse(0) { 0 }

    fun check(n: Long) : List<Int> = n.toString().reversed().foldIndexed("") { i, sum, c ->
            val temp = (c.toInt() - '0'.toInt()) shl 1
            sum + if (i % 2 == 0) c else ((if (temp > 10) temp - 9 else temp) + '0'.toInt()).toChar()
        }.reversed().map { it - '0' }
}