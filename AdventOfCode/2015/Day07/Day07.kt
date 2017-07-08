import java.util.*
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

/**
 * Created by MewX on 7/8/2017.
 * http://adventofcode.com/2015/day/7
 */

/**
 * This function takes a variable name or a constant,
 * to tell whether it is a variable name or not.
 * If not, it should be a constant value.
 */
fun isVar(request: String): Boolean = request[0].isLetter()

fun isReady(request: String, registers: MutableMap<String, Short>): Boolean = registers.containsKey(request)

fun opAssign(value: String, name: String, registers: MutableMap<String, Short>): Boolean {
    val op: Short = if (!isVar(value)) value.toInt().toShort() else if (isReady(value, registers)) registers[value]!! else return false
    registers[name] = op
    return true
}

fun opNot(value: String, name: String, registers: MutableMap<String, Short>): Boolean {
    val op: Short = if (!isVar(value)) value.toInt().toShort() else if (isReady(value, registers)) registers[value]!! else return false
    registers[name] = op.inv()
    return true
}

fun opAnd(value1: String, value2: String, name: String, registers: MutableMap<String, Short>): Boolean {
    val op1: Short = if (!isVar(value1)) value1.toInt().toShort() else if (isReady(value1, registers)) registers[value1]!! else return false
    val op2: Short = if (!isVar(value2)) value2.toInt().toShort() else if (isReady(value2, registers)) registers[value2]!! else return false
    registers[name] = op1 and op2
    return true
}

fun opOr(value1: String, value2: String, name: String, registers: MutableMap<String, Short>): Boolean {
    val op1: Short = if (!isVar(value1)) value1.toInt().toShort() else if (isReady(value1, registers)) registers[value1]!! else return false
    val op2: Short = if (!isVar(value2)) value2.toInt().toShort() else if (isReady(value2, registers)) registers[value2]!! else return false
    registers[name] = op1 or op2
    return true
}

fun lshift(value1: String, value2: String, name: String, registers: MutableMap<String, Short>): Boolean {
    val op1: Short = if (!isVar(value1)) value1.toInt().toShort() else if (isReady(value1, registers)) registers[value1]!! else return false
    val op2: Short = if (!isVar(value2)) value2.toInt().toShort() else if (isReady(value2, registers)) registers[value2]!! else return false
    registers[name] = java.lang.Short.toUnsignedInt(op1).shl(java.lang.Short.toUnsignedInt(op2)).toShort()
    return true
}

fun rshift(value1: String, value2: String, name: String, registers: MutableMap<String, Short>): Boolean {
    val op1: Short = if (!isVar(value1)) value1.toInt().toShort() else if (isReady(value1, registers)) registers[value1]!! else return false
    val op2: Short = if (!isVar(value2)) value2.toInt().toShort() else if (isReady(value2, registers)) registers[value2]!! else return false
    registers[name] = java.lang.Short.toUnsignedInt(op1).shr(java.lang.Short.toUnsignedInt(op2)).toShort()
    return true
}

fun opDispatcher(op: String, registers: MutableMap<String, Short>, list: LinkedList<String>) {
    // parse input op
    val results = Regex("(.+?)->(.+)").find(op) ?: return
    val leftOp = results.groupValues[1].trim()
    val name = results.groupValues[2].trim()

    // sub-regex for left op
    val rxAnd = Regex("(.+?) AND (.+)")
    val rxOr = Regex("(.+?) OR (.+)")
    val rxLshift = Regex("(.+?) LSHIFT (.+)")
    val rxRshift = Regex("(.+?) RSHIFT (.+)")
    val rxNot = Regex("NOT (.+)")

    if (rxAnd.matches(leftOp)) {
        val temp = rxAnd.find(leftOp)
        if (!opAnd(temp!!.groupValues[1].trim(), temp.groupValues[2].trim(), name, registers)) list.addLast(op)
    } else if (rxOr.matches(leftOp)) {
        val temp = rxOr.find(leftOp)
        if (!opOr(temp!!.groupValues[1].trim(), temp.groupValues[2].trim(), name, registers)) list.addLast(op)
    } else if (rxLshift.matches(leftOp)) {
        val temp = rxLshift.find(leftOp)
        if (!lshift(temp!!.groupValues[1].trim(), temp.groupValues[2].trim(), name, registers)) list.addLast(op)
    } else if (rxRshift.matches(leftOp)) {
        val temp = rxRshift.find(leftOp)
        if (!rshift(temp!!.groupValues[1].trim(), temp.groupValues[2].trim(), name, registers)) list.addLast(op)
    } else if (rxNot.matches(leftOp)) {
        val temp = rxNot.find(leftOp)
        if (!opNot(temp!!.groupValues[1].trim(), name, registers)) list.addLast(op)
    } else {
        if (!opAssign(leftOp, name, registers)) list.addLast(op)
    }
}

fun main(args: Array<String>) {
    // init map
    val map = mutableMapOf<String, Short>()
    val list = LinkedList<String>()

    // read operations
    val s = Scanner(System.`in`)
    while (s.hasNextLine()) {
        val line = s.nextLine()
        println(line)
        opDispatcher(line, map, list)
    }
    s.close()

    // finish the remaining tasks
    while (list.isNotEmpty()) {
        opDispatcher(list.poll(), map, list)
    }

    // dump all variables
    map.forEach { t, u -> println(t + ":" + java.lang.Short.toUnsignedInt(u)) }
}
