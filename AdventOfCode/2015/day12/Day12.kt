import java.util.*

/**
 * Created by MewX on 7/10/2017.
 */

fun sumLine(str: String): Int = Regex("-?\\d+").findAll(str).sumBy { it.value.toInt() }

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    var sum = 0
    while (s.hasNextLine()) {
        sum += sumLine(s.nextLine())
    }
    s.close()

    println("part 1: $sum")
}
