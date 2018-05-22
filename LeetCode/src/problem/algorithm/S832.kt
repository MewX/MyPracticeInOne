package problem.algorithm

/**
 * Just noticed that LeetCode supported Kotlin, so I wanted to have a try!
 */
class S832 {
    fun flipAndInvertImage(A: Array<IntArray>): Array<IntArray> = A.copyOf()
            .map { row -> row.mapIndexed { index, _ -> (row[row.size - 1 - index] + 1) % 2 }.toIntArray() }
            .toTypedArray()
}