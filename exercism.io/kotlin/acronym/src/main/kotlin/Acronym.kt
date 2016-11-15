object Acronym {
    fun generate(p: String) : String = p
            .replace(Regex("([a-z])([A-Z])"), "$1 $2")
            .split(Regex("[\\s-]+"))
            .map { it[0].toUpperCase() }
            .joinToString("")
}
