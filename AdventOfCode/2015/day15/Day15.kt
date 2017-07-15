import java.util.*

/**
 * Created by MewX on 7/13/2017.
 *
 * Two levels of combination, it is not easy.
 * This program is dynamic encoding, which means any numbers of input are allowed.
 */

data class Ingredient(val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int)
val PART = 2

fun benchmark(selected: Map<String, Int>, db: Map<String, Ingredient>): Int {
    val allCapacity = Math.max(0, selected.map { db[it.key]!!.capacity * it.value }.sum())
    val allDur= Math.max(0, selected.map { db[it.key]!!.durability * it.value }.sum())
    val allFlavor = Math.max(0, selected.map { db[it.key]!!.flavor * it.value }.sum())
    val allTexture = Math.max(0, selected.map { db[it.key]!!.texture * it.value }.sum())
    val allCalories = Math.max(0, selected.map { db[it.key]!!.calories * it.value }.sum())
    val result = if (PART == 1 || PART == 2 && allCalories == 500) allCapacity * allDur * allFlavor * allTexture else 0

    selected.forEach { print(it.key + " (" + it.value + "), ") }
    println("-> $result")
    return result
}

fun permuteNumber(totalNumber: Int, parts: Int, call: (List<Int>) -> Int): Int {
//    val allocations = IntArray(parts)
//    (0..allocations.size-1).forEach { allocations[it] = 1 }
    if (parts == 1) return call(intArrayOf(totalNumber).toList())
    val remain = totalNumber - parts
    return (0..remain).map { i -> permuteNumber(remain - i + parts - 1, parts - 1) { call(intArrayOf(i + 1).plus(it).toList()) } }.max() ?: 0
}

/**
 * @selected: selected indices
 * @lst: all name list
 * @db: objects
 */
fun permuteSelected(selected: LinkedList<Int>, lst: List<String>, db: Map<String, Ingredient>): Int {
    var largest = 0

    val idxStart = (selected.max() ?: -1) + 1
    if (idxStart >= db.size) return 0
    for (i in idxStart..db.size - 1) {
        selected.push(i)
//        largest = Math.max(largest, benchmark(selected.map { lst[it] }.toSet(), db))
        largest = Math.max(largest, permuteNumber(100, selected.size) { benchmark(selected.map { lst[it] }.toList().zip(it).toMap(), db) })
        largest = Math.max(largest, permuteSelected(selected, lst, db))
        selected.pop()
    }
    return largest
}

fun main(args: Array<String>) {
    val data = mutableMapOf<String, Ingredient>()
    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        val line = (Regex("(\\w+): capacity ([\\-\\d]+), durability ([\\-\\d]+), flavor ([\\-\\d]+), texture ([\\-\\d]+), calories ([\\-\\d]+)").find(s.nextLine()) ?: continue).groupValues
        data[line[1]] = Ingredient(line[2].toInt(), line[3].toInt(), line[4].toInt(), line[5].toInt(), line[6].toInt())
    }
    s.close()

    println("Part $PART: " + permuteSelected(LinkedList(), data.keys.toList(), data))

}
