object Hamming {
    fun compute(src: String, targ: String) : Int =
        if (src.length != targ.length) throw IllegalArgumentException()
        else src.indices.filter { src[it] != targ[it] }.count()
}