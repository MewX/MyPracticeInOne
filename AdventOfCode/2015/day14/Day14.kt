import java.util.*

/**
 * Created by MewX on 7/11/2017.
 *
 * Usage: kotlin Day14Kt 2503 < input.txt
 */

fun calcDistanceAfter(v: Int, tFly: Int, tRest: Int, time: Int): Int {
    val tRound = tFly + tRest
    var ret = (time / tRound) * (v * tFly)
    ret += Math.min(time % tRound, tFly) * v
    return ret
}

fun main(args: Array<String>) {
    val targetSecond = args[0].toInt()
    val s = Scanner(System.`in`)

    var longest = 0
    while (s.hasNextLine()) {
        val line = s.nextLine()

        val matches = (Regex("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+)").find(line) ?: continue).groupValues
        val name = matches[1]
        val speed=  matches[2].toInt()
        val tFly = matches[3].toInt()
        val tRest = matches[4].toInt()

        val calc = calcDistanceAfter(speed, tFly, tRest, targetSecond)
        println("$name: $calc")
        longest = Math.max(longest, calc)
    }
    s.close()

    println("Part 1: $longest")

}
