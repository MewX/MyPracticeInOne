import java.util.*

/**
 * Created by MewX on 7/8/2017.
 * http://adventofcode.com/2015/day/8
 */

fun countActual(str: String): Int {
    var count = 0

    var i = 1
    while (i < str.length - 1) {
        // skip the quoting marks
        count ++
        if (str[i] == '\\') {
            i ++
            if (str[i] != '\\' && str[i] != '\"') i += 2 // == 'x'
        }
        i ++
    }
    println("==> " + count)
    return count
}

fun main(args: Array<String>) {
    var sum1 = 0
    var sum2 = 0

    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        val line = s.nextLine()
        println(line)

        sum1 += line.length
        sum2 += countActual(line)
    }
    s.close()

    println(sum1)
    println(sum2)
    println(sum1 - sum2)
}
