package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S784Test {

    @Test
    fun example1() {
        assertEquals(S784().letterCasePermutation("a1b2").toSet(),
                setOf("a1b2", "a1B2", "A1b2", "A1B2"))
    }

    @Test
    fun example2() {
        assertEquals(S784().letterCasePermutation("3z4").toSet(),
                setOf("3z4", "3Z4"))
    }

    @Test
    fun example3() {
        assertEquals(S784().letterCasePermutation("12345").toSet(),
                setOf("12345"))
    }
}