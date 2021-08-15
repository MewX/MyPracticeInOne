package problem.algorithm

class S824 {
    fun toGoatLatin(S: String): String = S
            .split(" ")
            .mapIndexed { index, s ->
                (if (s[0].toLowerCase() !in setOf('a', 'e', 'i', 'o', 'u'))
                    s.substring(1) + s[0]
                else s).plus("ma").plus((0..index).map { 'a' }.joinToString(""))
            }
            .joinToString(" ")
}