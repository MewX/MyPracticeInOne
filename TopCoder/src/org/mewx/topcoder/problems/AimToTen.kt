package org.mewx.topcoder.problems

/**
 * Created by MewX on 8/28/2016.
 */
class AimToTen() {
    fun need(marks: IntArray): Int {
        // multiply 10 first
        for (idx: Int in marks.indices) marks[idx] *= 10

        // use formula:
        // sum + n * 10 >= 9.5 * (n + size)
        // 0.5 * n >= 9.5 * size - sum
        // n >= 19 * size - 2 * sum
        val result: Int = 190 * marks.size - 2 * marks.sum();
        return when {
            result < 0 -> 0
            result % 10 != 0 -> result / 10 + 1
            else -> result / 10;
        }
    }
}