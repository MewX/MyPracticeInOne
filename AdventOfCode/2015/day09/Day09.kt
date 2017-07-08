import java.util.*

/**
 * Created by MewX on 7/8/2017.
 * This is a typical TSP problem (NP hard)
 */

// return value <smallest, biggest>
fun dfs(table: Array<IntArray>, path: LinkedList<Int>, sum: Int): Pair<Int, Int> {
    if (table.size == path.size) {
        path.forEach { print(it.toString() + ", ") }
        println(sum)
        return Pair(sum, sum)
    }

    var smallest = Int.MAX_VALUE
    var biggest = Int.MIN_VALUE
    var i = 0
    while (i < table.size) {
        if (!path.contains(i)) {
            val tempSum = sum + (if (path.isNotEmpty()) table[path.last()][i] else 0)
            path.addLast(i)

            val temp = dfs(table, path, tempSum)
            smallest = Math.min(temp.first, smallest)
            biggest = Math.max(temp.second, biggest)
            path.removeLast()
        }
        i ++
    }
    return Pair(smallest, biggest)
}

fun main(args: Array<String>) {
    var cityIdx = 0
    val cityMap = mutableMapOf<String, Int>() // cityName, cityIdx
    val table = Array(8, {IntArray(8)}) // sample: 3; input: 8

    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        val line = s.nextLine()
        println(line)

        val results = Regex("(.+?) to (.+?) = (\\d+)").find(line) ?: continue
        val city1 = results.groupValues[1]
        val city2 = results.groupValues[2]
        val dist = results.groupValues[3].toInt()

        val idx1 = cityMap.getOrElse(city1) {cityIdx ++}
        println(city1 + " = " + idx1)
        cityMap.putIfAbsent(city1, idx1)
        val idx2 = cityMap.getOrElse(city2) {cityIdx ++}
        println(city2 + " = " + idx2)
        println("dist: " + dist)
        cityMap.putIfAbsent(city2, idx2)
        table[idx1][idx2] = dist
        table[idx2][idx1] = dist
    }
    s.close()

    val result = dfs(table, LinkedList<Int>(), 0)
    println("part 1: " + result.first)
    println("part 2: " + result.second)
}
