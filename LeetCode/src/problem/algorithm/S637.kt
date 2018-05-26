package problem.algorithm

import utils.TreeNode

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class S637 {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        // level starts from 0
        val numMap = mutableMapOf<Int, Int>() // store the number of nodes in a level
        val sumMap = mutableMapOf<Int, Long>() // store the sum of nodes in a level
        traverse(root, 0, numMap, sumMap)
        return if (numMap.isEmpty()) doubleArrayOf() else (0..numMap.keys.max()!!).map { sumMap[it]!! * 1.0 / numMap[it]!! }.toDoubleArray()
    }

    private fun traverse(root: TreeNode?, level: Int, numMap: MutableMap<Int, Int>, sumMap: MutableMap<Int, Long>) {
        if (root == null) return

        numMap[level] = numMap.getOrDefault(level, 0) + 1
        sumMap[level] = sumMap.getOrDefault(level, 0) + root.`val`
        traverse(root.left, level + 1, numMap, sumMap)
        traverse(root.right, level + 1, numMap, sumMap)
    }
}