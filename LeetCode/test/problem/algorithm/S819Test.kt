package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S819Test {

    @Test
    fun testMostCommonWord() {
        assertEquals(S819().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", arrayOf("hit")), "ball")
    }

    @Test
    fun failedTest() {
        assertEquals(S819().mostCommonWord("Bob. hit, ball", arrayOf("hit", "bob")), "ball")
    }
}