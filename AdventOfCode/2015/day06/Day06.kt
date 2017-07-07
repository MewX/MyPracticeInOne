import java.util.*

/**
 * Created by MewX on 7/7/2017.
 * http://adventofcode.com/2015/day/6
 *
 * Input should be System.in stream from file.
 *
 */

fun opOn(xLeft: Int, xRight: Int, yTop: Int, yBottom: Int, matrix: Array<BooleanArray>) {
    for (i in xLeft..xRight) {
        for (j in yTop..yBottom) {
            matrix[i][j] = true
        }
    }
}

fun opOff(xLeft: Int, xRight: Int, yTop: Int, yBottom: Int, matrix: Array<BooleanArray>) {
    for (i in xLeft..xRight) {
        for (j in yTop..yBottom) {
            matrix[i][j] = false
        }
    }
}

fun opToggle(xLeft: Int, xRight: Int, yTop: Int, yBottom: Int, matrix: Array<BooleanArray>) {
    for (i in xLeft..xRight) {
        for (j in yTop..yBottom) {
            matrix[i][j] = !matrix[i][j]
        }
    }
}

fun opParse(str: String, matrix: Array<BooleanArray>) {
    if (str.isEmpty()) return

    // parse integers
    val result = Regex("(.+?)(\\d+?),(\\d+?) through (\\d+?),(\\d+)").find(str) ?: return
    val x1 = result.groupValues[2].toInt()
    val y1 = result.groupValues[3].toInt()
    val x2 = result.groupValues[4].toInt()
    val y2 = result.groupValues[5].toInt()
    val xLeft = Math.min(x1, x2)
    val xRight = Math.max(x1, x2)
    val yTop = Math.min(y1, y2)
    val yBottom = Math.max(y1, y2)

    if (result.groupValues[1].contains("on")) {
        opOn(xLeft, xRight, yTop, yBottom, matrix)
    } else if (result.groupValues[1].contains("off")) {
        opOff(xLeft, xRight, yTop, yBottom, matrix)
    } else {
        opToggle(xLeft, xRight, yTop, yBottom, matrix)
    }
}

fun main(args: Array<String>) {
    // init matrix
    val matrix = Array(1000, {BooleanArray(1000)})
    matrix.map { it.map { false } }

    // read operations
    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        opParse(s.nextLine(), matrix)
    }
    println("Lit: " + matrix.sumBy { it.count { it == true } })
}
