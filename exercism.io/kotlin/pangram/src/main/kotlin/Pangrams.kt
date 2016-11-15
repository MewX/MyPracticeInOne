object Pangrams {
    fun isPangram(str: String) : Boolean {
        val lower = str.toLowerCase()
        return ('a'..'z').all { lower.contains(it) }
    }
}