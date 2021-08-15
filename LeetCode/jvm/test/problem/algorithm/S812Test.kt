package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S812Test {

    @Test
    fun testLargestTriangleArea() {
        assertEquals(S812().largestTriangleArea(arrayOf(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(1, 0),
                intArrayOf(0, 2), intArrayOf(2, 0))), 2.0, 0.000001)
    }

    @Test
    fun wrongAnswer1() {
        assertEquals(S812().largestTriangleArea(arrayOf(intArrayOf(1, 0), intArrayOf(0, 0),
                intArrayOf(0, 1))), 0.5, 0.000001)
    }
}