object Raindrops {
    val map = mapOf(3 to 'i', 5 to 'a', 7 to 'o')
    fun convert(n: Int) : String = map
            .filter { n % it.key == 0 }
            .map { "Pl${it.value}ng" }
            .joinToString("")
            .let { if (it.isEmpty()) n.toString() else it }
}