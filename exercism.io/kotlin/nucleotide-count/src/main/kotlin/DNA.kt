class DNA(val str: String) {
    val nucleotideCounts = mutableMapOf('A' to 0, 'C' to 0, 'G' to 0, 'T' to 0)
    init {
        str.forEach { nucleotideCounts.put(it, nucleotideCounts.getOrPut(it) { 0 } + 1) }
        if (nucleotideCounts.keys.count() > 4) throw IllegalArgumentException()
    }

    fun count(c: Char) : Int = nucleotideCounts.getOrElse(c) { throw IllegalArgumentException() }
}
