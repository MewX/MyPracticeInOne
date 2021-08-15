package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*
import utils.TreeNodeTools

class S637Test {

    @Test
    fun emptyTree() {
        assertEquals(S637().averageOfLevels(null), doubleArrayOf())
    }

    @Test
    fun example() {
        assertEquals(S637().averageOfLevels(TreeNodeTools.buildBinaryTree(arrayOf(3, 9, 20, null, null, 15, 7))),
                doubleArrayOf(3.0, 14.5, 11.0))
    }

    @Test
    fun failedTest() {
        // Int exceeded
        assertEquals(S637().averageOfLevels(TreeNodeTools.buildBinaryTree(arrayOf(2147483647, 2147483647, 2147483647))),
                doubleArrayOf(2147483647.0, 2147483647.0))

    }
}