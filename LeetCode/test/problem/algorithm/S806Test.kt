package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S806Test {

    @Test
    fun example1() {
        assertEquals(S806().numberOfLines(intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                "abcdefghijklmnopqrstuvwxyz"), intArrayOf(3, 60))
    }

    @Test
    fun example2() {
        assertEquals(S806().numberOfLines(intArrayOf(4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                "bbbcccdddaaa"), intArrayOf(2, 4))
    }
}