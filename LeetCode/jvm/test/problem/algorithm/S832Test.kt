package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S832Test : TestBase() {

    @Test
    fun example1() {
        val result = S832().flipAndInvertImage(arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 0, 1), intArrayOf(0, 0, 0)))
        assertEquals(result, arrayOf(intArrayOf(1, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1)))
    }

    @Test
    fun example2() {
        val result = S832().flipAndInvertImage(arrayOf(intArrayOf(1, 1, 0, 0), intArrayOf(1, 0, 0, 1), intArrayOf(0, 1, 1, 1), intArrayOf(1, 0, 1, 0)))
        assert2DArrayEqual(result, arrayOf(intArrayOf(1, 1, 0, 0), intArrayOf(0, 1, 1, 0), intArrayOf(0, 0, 0, 1), intArrayOf(1, 0, 1, 0)))
    }
}