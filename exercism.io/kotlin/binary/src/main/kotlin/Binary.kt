object Binary {
    fun toDecimal(bin: String) : Int = when {
        bin.contains(Regex("[^01]")) -> 0
        else -> bin.fold(0, {sum, c -> (sum shl 1) + (c - '0') })
    }
}