fun main(args: Array<String>) {
    val num1 = input.count {
        !it.contains(Regex("ab|cd|pq|xy"))
        && it.count { "aeiou".contains(it) } >= 3
        && it.fold(Pair<Boolean, Char>(false, ' ')) { p, ch -> Pair<Boolean, Char>(p.first || p.second == ch, ch) }.first
    }
    println("part1: $num1")

    val num2 = input.count {
        it.let {
            var result1 = false
            for (i in 0..it.length - 2)
                if (it.lastIndexOf(it.substring(i, i + 2)) - i >= 2)
                    result1 = true

            if (result1) {
                var result2 = false
                "abcdefghijklmnopqrstuvwxyz".toList().forEach {
                    c ->
                    if (it.contains(Regex("$c.$c")))
                        result2 = true
                }
                result2
            } else false
        }
    }
    println("part2: $num2")
}
