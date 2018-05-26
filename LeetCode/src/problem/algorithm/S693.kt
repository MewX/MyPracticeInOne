package problem.algorithm

class S693 {
    fun hasAlternatingBits(n: Int): Boolean {
        val str = n.toString(2)
        return !(1 until str.length)
                .map { str[it] == str[it - 1] }
                .contains(true)
    }
}