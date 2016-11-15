object Scrabble {
    val table = mapOf("aeioulnrst" to 1, "dg" to 2, "bcmp" to 3, "fhvwy" to 4, "k" to 5, "jx" to 8, "qz" to 10)
            .flatMap { it.key.map { letter -> letter to it.value } }
            .toMap()

    fun scoreWord(str: String) : Int = str.sumBy { table[it.toLowerCase()] ?: 0 }
}