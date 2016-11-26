fun main(args: Array<String>) {
    val sets = mutableSetOf<Pair<Int, Int>>()
    var x = 0
    var y = 0
    sets.add(Pair(x, y))
    input.forEach {
        when (it) {
            'v' -> y --
            '^' -> y ++
            '<' -> x --
            '>' -> x ++
        }
        sets.add(Pair(x, y))
    }
    println("part1: ${sets.size}")
}
