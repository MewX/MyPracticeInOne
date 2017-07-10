import java.util.*

/**
 * Created by MewX on 7/10/2017.
 */

fun sumLine(str: String): Int = Regex("-?\\d+").findAll(str).sumBy { it.value.toInt() }

fun removeRedObjects(sb: StringBuilder): StringBuilder {
    var str = sb
    do {
        val idx = str.indexOf(":\"red\"")
        if (idx == -1) break
        println("Found red in: $idx")

        // find beginning index
        var remaining = 1 // the brace counter
        var rmBeg = idx
        while (rmBeg >= 0 && remaining != 0) {
            rmBeg --
            if (str[rmBeg] == '}') remaining ++
            else if (str[rmBeg] == '{') remaining --
        }

        // find ending index
        remaining = 1
        var rmEnd = idx + 4
        while (rmEnd < str.length && remaining != 0) {
            rmEnd ++
            if (str[rmEnd] == '{') remaining ++
            else if (str[rmEnd] == '}') remaining --
        }
        println("Removing: " + str.subSequence(rmBeg..rmEnd))
        str = StringBuilder(str.removeRange(rmBeg..rmEnd))
//        println("After removing: " + str)
    } while (true)
    return str
}

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    var sum1 = 0
    var sum2 = 0
    while (s.hasNextLine()) {
        val currentLine = s.nextLine()
        sum1 += sumLine(currentLine)

        val sb = StringBuilder(currentLine)
        sum2 += sumLine(removeRedObjects(sb).toString())
    }
    s.close()

    println("part 1: $sum1")
    println("part 2: $sum2")
}
