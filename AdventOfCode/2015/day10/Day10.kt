import java.util.*

/**
 * Created by MewX on 7/8/2017.
 * http://adventofcode.com/2015/day/10
 *
 * Part 1 input: 1113222113 with 40 loop
 * Part 2 input: 1113222113 with 50 loop
 */

fun main(args: Array<String>) {
//    val s = Scanner(System.`in`)
//    var temp = s.nextLine()
    var temp = args[0]
    println("Input: $temp")
//    s.close()

    val sb = StringBuilder()
    for (times in 1..50) {
        var prev = temp[0]
        var idx = 1
        var counter = 1
        while (idx < temp.length) {
            if (temp[idx] == prev) {
                counter ++
            } else {
                sb.append(counter).append(prev)
                prev = temp[idx]
                counter = 1
            }

            idx ++
        }
        sb.append(counter).append(prev)

        // move to a new round
        temp = sb.toString()
        sb.setLength(0)
        println(String.format("Round %d: %s (%d)", times, "change to variable temp", temp.length))
    }

}
