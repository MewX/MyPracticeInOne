object ETL {
    fun transform(old: Map<Int, List<String>>) : Map<String, Int> =
            old.flatMap {
                it.value.map {
                   word -> word.toLowerCase() to it.key
                }
            }.toMap()
}