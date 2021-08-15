package problem.algorithm

class S806 {
    fun numberOfLines(widths: IntArray, S: String): IntArray {
        var lineCount = 1
        var lastLineCount = 0

        S.forEach {
            if (lastLineCount + widths[it - 'a'] > 100) {
                lineCount++
                lastLineCount = 0
            }

            lastLineCount += widths[it - 'a']
        }
        return intArrayOf(lineCount, lastLineCount)
    }
}
