package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S821Test {

    @Test
    fun testShortestToChar() {
        assertEquals(S821().shortestToChar("loveleetcode", 'e'), intArrayOf(3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0))
    }
}