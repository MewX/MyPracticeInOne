object Atbash {
    private val table = ('a'..'z').toList()

    fun encode(str: String) : String = str
            .toLowerCase()
            .filter { it in 'a'..'z' || it in '0'..'9' }
            .map { if (it in 'a'..'z') table[25 - (it - 'a')] else it }
            .foldIndexed("") { i, sum, c -> sum + (if (i > 0 && i % 5 == 0) " " else "") + c }

    fun decode(str: String) : String = str
            .toLowerCase()
            .replace(Regex("[^0-9a-z]"), "")
            .map { if (it in 'a'..'z') table[25 - (it - 'a')] else it }
            .joinToString("")
}