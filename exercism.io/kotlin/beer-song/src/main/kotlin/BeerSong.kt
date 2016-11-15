object BeerSong {
    val idxList: List<String> = listOf("no more bottles", "1 bottle").union((2..99).map { it.toString() + " bottles" }.toSet()).toList()

    fun verse(idx: Int) : String = when {
        idx < 0 || idx > 99 -> throw IllegalArgumentException()
        else -> "${idxList[idx].capitalize()} of beer on the wall, ${idxList[idx]} of beer.\n${part3(idx)} ${idxList[(idx + 99) % 100]} of beer on the wall.\n"
    }

    private fun part3(i: Int) : String = when (i) {
        0 -> "Go to the store and buy some more,"
        else -> "Take ${if (i == 1) "it" else "one"} down and pass it around,"
    }

    fun verses(a: Int, b: Int) : String = when {
        a > 99 || b > 99 || a < 0 || b < 0 -> throw IllegalArgumentException()
        else -> (Math.min(a, b)..Math.max(a, b)).map { verse(it) }.reversed().joinToString(separator="\n")
    }

    val lyrics = verses(0, 99) // both (0, 99) and (99, 0) work!! :P
}
