/**
 * Created by MewX on 7/10/2017.
 * http://adventofcode.com/2015/day/11
 *
 * part 1: vzbxkghb
 */

/**
 * The function for escaping [iol]
 */
fun shiftToValidStringIfInvalid(str: StringBuilder) {
    // find first invalid char first
    var idx = 0
    while (idx < str.length) {
        val invalidSet = setOf('i', 'o', 'l')
        if (str[idx] in invalidSet) {
            // found!
            while (invalidSet.contains(str[idx])) str[idx] = str[idx] + 1 // impossible to be 'z'
            // set all remaining into 'a'
            while (++ idx < str.length) str[idx] = 'a'
            break
        }
        idx ++
    }
}

fun getNextChar(c: Char): Char = if (c == 'z') 'a' else c + 1

fun validate(str: StringBuilder): Boolean {
    // containing AA or BB or etc.
    if ((0..str.length - 2).filter { str[it] == str[it + 1] }.map { str[it].toString() + str[it + 1] }.toSet().size < 2) return false

    // containing ABC etc.
    // e.g. xyz (1, 1), yza (1, -25), xab (-25, 1)
    // return (1..str.length - 3).count { getNextChar(str[it]) == str[it + 1] && getNextChar(str[it + 1]) == str[it + 2] } != 0
    return (1..str.length - 3).count { str[it] + 1 == str[it + 1] && str[it + 1] + 1 == str[it + 2] } != 0
}

fun main(args: Array<String>) {
    val str = StringBuilder(args[0])
    shiftToValidStringIfInvalid(str)

    // only add 1 to the last char
    while (!validate(str)) {
        print("Working on \"$str\" -> ")

        // add 1
        var idx = str.length - 1 // from right to left
        while (idx >= 0) {
            str[idx] = getNextChar(str[idx])
            if (str[idx] == 'a') idx --
            else break
        }
        shiftToValidStringIfInvalid(str)
        println(str)
    }
}
