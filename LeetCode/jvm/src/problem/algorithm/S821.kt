package problem.algorithm

class S821 {
    fun shortestToChar(S: String, C: Char): IntArray = S
            .mapIndexed { index, _ ->
                val left = S.indexOf(C, index)
                val right = S.lastIndexOf(C, index)
                Math.min(if (left < 0) Int.MAX_VALUE else left - index, if (right < 0) Int.MAX_VALUE else index - right)
            }
            .toIntArray()
}