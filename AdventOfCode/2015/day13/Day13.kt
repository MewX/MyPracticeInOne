import java.util.*

/**
 * Created by MewX on 7/11/2017.
 */

fun evaluate(seq: LinkedList<Int>, table: Array<IntArray>): Int = seq.mapIndexed { i, cur ->
    table[cur][seq[if (i == 0) seq.size - 1 else i - 1]] + table[cur][seq[if (i == seq.size - 1) 0 else i + 1]]
}.sum()

fun evolve(seq: LinkedList<Int>, pool: LinkedList<Int>, table: Array<IntArray>): Int {
    if (pool.size == 0) {
        seq.forEach(::print)
        val ret = evaluate(seq, table)
        println(" -> $ret")
        return ret
    }

    var largest = Int.MIN_VALUE
    for (i in 0..pool.size - 1) {
        seq.push(pool[i])
        pool.removeAt(i)
        largest = Math.max(largest, evolve(seq, pool, table))
        pool.add(i, seq.poll())
    }
    return largest
}

fun main(args: Array<String>) {
    // build relationship from input
    val s = Scanner(System.`in`)
    val dim = 8 // sample: 4; input: 8
    var pIdx = 0
    val pMap = mutableMapOf<String, Int>() // peopleName, peopleIdx
    val table = Array(dim, {IntArray(dim)})
    while (s.hasNextLine()) {
        val line = s.nextLine()
        println(line)

        val results = (Regex("(\\w+) would ([gl])\\w\\w\\w (\\d+) happiness units by sitting next to (\\w+)").find(line) ?: continue).groupValues
        val name0 = results[1]
        val isGain: Boolean = results[2] == "g"
        val value = (if (isGain) 1 else -1) * results[3].toInt()
        val name1 = results[4]

        val idx0 = pMap.getOrElse(name0) {pIdx ++}
        pMap.putIfAbsent(name0, idx0)
        val idx1 = pMap.getOrElse(name1) {pIdx ++}
        pMap.putIfAbsent(name1, idx1)
        table[idx0][idx1] = value // one way value
    }
    s.close()

    table.forEach {
        row -> row.forEach {
            print(it)
            print("|")
        }
        println()
    }

    // find best combination
    // fix the first number as '0', and construct the rest numbers
    // NOTE: this will still cause double unnecessary steps
    println("part 1: " + evolve(LinkedList<Int>(arrayListOf(0)), LinkedList((1..table.size-1).toMutableList()), table))

}
