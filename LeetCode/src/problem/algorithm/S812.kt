package problem.algorithm

class S812 {
    fun largestTriangleArea(points: Array<IntArray>): Double {
        var max = Double.MIN_VALUE
        for (i in 0 until points.size - 2) {
            for (j in i + 1 until points.size - 1) {
                val x1 = 1.0 * points[i][0]
                val y1 = 1.0 * points[i][1]
                val x2 = 1.0 * points[j][0]
                val y2 = 1.0 * points[j][1]
                val deltaX = x1 - x2
                val deltaY = y1 - y2
                val bottom = Math.sqrt(deltaX * deltaX + deltaY * deltaY)
                val theK = if (Math.abs(deltaX) < 0.000001) 10000000000.0 else deltaY / deltaX
                val theB = y1 - theK * x1
                for (k in j + 1 until points.size) {
//                    System.out.format("%d %d %d -> ", i, j, k)
                    max = Math.max(max, calcArea(bottom, theK, theB, points[k]))
//                    System.out.println(calcArea(bottom, theK, theB, points[k]))
                }
            }
        }
        return max
    }

    private fun calcArea(bottom: Double, k: Double, b: Double, point: IntArray): Double {
        // the line: kx - y + b = 0
        // therefore: a = k; b = -1; c = b
        val x = point[0]
        val y = point[1]
        val height = Math.abs(k * x - y + b) / Math.sqrt(k * k + 1)
        return bottom * height / 2.0
    }
}