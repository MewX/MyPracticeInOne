package problem.algorithm

class S819 {
    fun mostCommonWord(paragraph: String, banned: Array<String>): String = paragraph
            .split("\\s|!|\\?|'|,|;|\\.".toRegex())
            .groupingBy { it.toLowerCase() }
            .eachCount()
            .filterNot { it.key.isEmpty() || banned.contains(it.key) }
            .maxByOrNull { it.value }!!.key
}