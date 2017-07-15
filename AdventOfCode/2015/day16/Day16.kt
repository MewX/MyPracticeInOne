import java.util.*

/**
 * Created by MewX on 7/14/2017.
 */

/**
 * @return <id, compoundsMap>
 */
fun lineToMap(line: String): Pair<Int, Map<String, Int>>? {
    val lineComponents = (Regex("Sue (\\d+):(.+)").find(line) ?: return null).groupValues
    val map = Regex("(\\w+): (\\d+)").findAll(lineComponents[2]).map { it.groupValues[1] to it.groupValues[2].toInt() }.toMap()
    return Pair(lineComponents[1].toInt(), map)
}

fun compToRequirement(line: Map<String, Int>): Boolean {
    val preset = mapOf("children" to 3, "cats" to 7, "pomeranians" to 3, "akitas" to 0, "vizslas" to 0,
                                    "goldfish" to 5, "trees" to 3, "cars" to 2, "perfumes" to 1)
    return line.count { it.value != preset.getOrDefault(it.key, 0) } == 0
}

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        val line = lineToMap(s.nextLine()) ?: continue
        if (compToRequirement(line.second)) {
            println("Aunt " + line.first + " is fine!")
        }
    }
    s.close()

}
