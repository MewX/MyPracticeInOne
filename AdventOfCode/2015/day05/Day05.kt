fun main(args: Array<String>) {
    val num1 = input.count {
        !it.contains(Regex("ab|cd|pq|xy"))
        && it.count { "aeiou".contains(it) } >= 3
        && it.fold(Pair<Boolean, Char>(false, ' ')) { p, ch -> Pair<Boolean, Char>(p.first || p.second == ch, ch) }.first
    }
    println("part1: $num1")
}
