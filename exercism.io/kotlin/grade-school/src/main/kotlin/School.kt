class School {
    private val database = mutableMapOf<Int, MutableList<String>>()

    fun add(name: String, grade: Int) =
        database.getOrPut(grade) { mutableListOf<String>() }.add(name)

    fun db(): Map<Int, List<String>> = database

    fun grade(grade: Int): List<String> = database[grade]?: mutableListOf<String>()

    fun sort(): Map<Int, List<String>> = database.mapValues { it.value.sorted() }.toSortedMap()
}