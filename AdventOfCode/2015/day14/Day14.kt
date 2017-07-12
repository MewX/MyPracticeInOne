import java.util.*

/**
 * Created by MewX on 7/11/2017.
 *
 * Usage:
 * kotlin Day14Kt 2503 < input.txt
 */

data class Info(val name: String, val speed: Int, val tFly: Int, val tRest: Int)

fun calcDistanceAfter(info: Info, time: Int): Int {
    val tRound = info.tFly + info.tRest
    return (time / tRound) * (info.speed * info.tFly) + Math.min(time % tRound, info.tFly) * info.speed
}

fun main(args: Array<String>) {
    val targetSecond = args[0].toInt()
    val s = Scanner(System.`in`)

    var longest = 0
    val reindeers = mutableMapOf<Info, Int>() // obj, score
    while (s.hasNextLine()) {
        val line = s.nextLine()

        val matches = (Regex("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+)").find(line) ?: continue).groupValues
        val name = matches[1]
        val speed=  matches[2].toInt()
        val tFly = matches[3].toInt()
        val tRest = matches[4].toInt()
        val obj = Info(name, speed, tFly, tRest)
        reindeers[obj] = 0

        val calc = calcDistanceAfter(obj, targetSecond)
        println("$name: $calc")
        longest = Math.max(longest, calc)
    }
    s.close()
    println("Part 1: $longest")

    // calc part 2
    for (i in 1..targetSecond) {
        val scores = reindeers.map { it.key }.map { Pair(it, calcDistanceAfter(it, i)) }
        val maxScore = scores.map { it.second }.max()
        scores.filter { it.second == maxScore }.forEach { reindeers.computeIfPresent(it.first) { _, score -> score + 1 } }
    }
    println("Part 2: " + reindeers.map { it.value }.max())
}
