fun main(args: Array<String>) {
    val sets = mutableSetOf<Pair<Int, Int>>()
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0
    sets.add(Pair(x1, y1))
    input.forEachIndexed {
        i, c ->
        if (i % 2 == 0) {
            when (c) {
                'v' -> y1--
                '^' -> y1++
                '<' -> x1--
                '>' -> x1++
            }
            sets.add(Pair(x1, y1))
        } else {
            when (c) {
                'v' -> y2--
                '^' -> y2++
                '<' -> x2--
                '>' -> x2++
            }
            sets.add(Pair(x2, y2))
        }
    }

    println("part2: ${sets.size}")
}
