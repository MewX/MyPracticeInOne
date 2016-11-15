object Flattener {
    fun flatten(list: List<Any?>) : List<Any> = list.flatMap {
        if (it is List<Any?>) flatten(it)
        else listOf(it)
    }.filterNotNull()
}