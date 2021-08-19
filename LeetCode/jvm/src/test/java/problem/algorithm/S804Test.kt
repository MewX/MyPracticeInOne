package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S804Test {

    @Test
    fun example() {
        assertEquals(S804().uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg")), 2)
    }
}