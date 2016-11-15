object WordCount {
    fun phrase(str: String) : Map<String, Int> = Regex("[\\w\']+")
            .findAll(str.toLowerCase())
            .groupBy { it.value }
            .map { it.key to it.value.count() }
            .toMap()
}
