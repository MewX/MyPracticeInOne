package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S693Test {

    @Test
    fun example1() {
        assertEquals(S693().hasAlternatingBits(5), true)
    }

    @Test
    fun example2() {
        assertEquals(S693().hasAlternatingBits(7), false)
    }

    @Test
    fun example3() {
        assertEquals(S693().hasAlternatingBits(11), false)
    }

    @Test
    fun example4() {
        assertEquals(S693().hasAlternatingBits(10), true)
    }

}