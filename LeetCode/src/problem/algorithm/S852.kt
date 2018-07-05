package problem.algorithm

class S852 {

    fun peakIndexInMountainArray(A: IntArray): Int = (0 until A.size).maxBy { A[it] }!!

    fun peakIndexInMountainArray_Solution2(A: IntArray): Int {
        var max = Int.MIN_VALUE
        var iMax = 0
        A.forEachIndexed { index, i ->
            if (i > max) {
                max = i
                iMax = index
            }
        }
        return iMax
    }
}
