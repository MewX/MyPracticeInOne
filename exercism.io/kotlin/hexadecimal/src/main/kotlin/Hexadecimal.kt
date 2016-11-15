object Hexadecimal {
    private val table = (('0'..'9') + ('a'..'f')).mapIndexed { i, c -> c to i }.toMap()

    fun toDecimal(hex: String) : Int = when {
        hex.contains(Regex("[^0-9a-fA-F]")) -> 0
        else -> hex.toLowerCase().fold(0) {
            sum, c -> (sum shl 4) + (table[c] ?: 0)
        }
    }
}