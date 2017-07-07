import java.util.*

/**
 * Created by MewX on 7/7/2017.
 * http://adventofcode.com/2015/day/6
 *
 * Input should be System.in stream from file.
 *
 */

fun opOn(part: Int, xLeft: Int, xRight: Int, yTop: Int, yBottom: Int, matrix: Array<IntArray>) {
    for (i in xLeft..xRight) {
        (yTop..yBottom)
                .filter { matrix[i][it] == 0 || part == 2 }
                .forEach { matrix[i][it] += 1 }
    }
}

fun opOff(xLeft: Int, xRight: Int, yTop: Int, yBottom: Int, matrix: Array<IntArray>) {
    for (i in xLeft..xRight) {
        (yTop..yBottom)
                .filter { matrix[i][it] != 0 }
                .forEach { matrix[i][it] -= 1 }
    }
}

fun opToggle(part: Int, xLeft: Int, xRight: Int, yTop: Int, yBottom: Int, matrix: Array<IntArray>) {
    for (i in xLeft..xRight) {
        for (j in yTop..yBottom) {
            matrix[i][j] += 2
            if (part == 1) matrix[i][j] = matrix[i][j].inc().and(1)
        }
    }
}

fun opParse(part: Int, str: String, matrix: Array<IntArray>) {
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
        opOn(part, xLeft, xRight, yTop, yBottom, matrix)
    } else if (result.groupValues[1].contains("off")) {
        opOff(xLeft, xRight, yTop, yBottom, matrix)
    } else {
        opToggle(part, xLeft, xRight, yTop, yBottom, matrix)
    }
}

fun main(args: Array<String>) {
    // init matrix
    val matrix1 = Array(1000, {IntArray(1000)})
    val matrix2 = Array(1000, {IntArray(1000)})
    matrix1.map { it.map { 0 } }
    matrix2.map { it.map { 0 } }

    // read operations
    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        val line = s.nextLine()
        opParse(1, line, matrix1)
        opParse(2, line, matrix2)
    }
    s.close()

    println("Lit1: " + matrix1.sumBy { it.sum() })
    println("Lit2: " + matrix2.sumBy { it.sum() })
}
