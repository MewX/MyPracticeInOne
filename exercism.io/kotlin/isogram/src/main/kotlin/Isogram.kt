object Isogram {
    fun isIsogram(str: String) : Boolean = str
            .filter { it.isLetter() }
            .groupBy { it.toLowerCase() }
            .none { it.value.size > 1 }
}