object PascalsTriangle {
    fun computeTriangle(n: Int) = generateSequence(listOf(1)) {
        listOf(1) + (it zip it.drop(1)).map { it.first + it.second } + listOf(1)
    }.take(n).toList()
}