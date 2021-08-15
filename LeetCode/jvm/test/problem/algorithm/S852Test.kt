package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S852Test {

    @Test
    fun testExample1() {
        assertEquals(S852().peakIndexInMountainArray(intArrayOf(0, 1, 0)), 1)
    }

    @Test
    fun testExample2() {
        assertEquals(S852().peakIndexInMountainArray(intArrayOf(0, 2, 1, 0)), 1)
    }
}