object PigLatin {
    fun translate(str: String): String = str.split(Regex(" +")).map {
        str -> when {
            "a,e,i,o,u,yt,xr".split(",").toSet().any { str.startsWith(it) } -> str
            else -> "ch,qu,squ,thr,th,sch".split(",").fold("") {
                sum, ch ->
                if (sum.isNullOrEmpty() && str.startsWith(ch)) str.substring(ch.length) + ch else sum
            }.let { if (it.isEmpty()) str.substring(1) + str[0] else it }
        } + "ay"
    }.joinToString(" ")
}