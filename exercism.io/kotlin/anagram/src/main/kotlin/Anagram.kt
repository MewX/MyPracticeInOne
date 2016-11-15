class Anagram(val src: String) {
    fun match(list: List<String>): List<String> = list
            .distinctBy(String::toLowerCase)
            .filter { it.groupBy { it }.equals(src.toLowerCase().groupBy { it } ) && !it.equals(src.toLowerCase()) }
}