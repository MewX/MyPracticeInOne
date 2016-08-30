package org.mewx.topcoder.problems

/**
 * Created by MewX on 8/30/2016.
 */
class StringSegmentKt {

    fun average(s: String): Double {
        var partsCount = 0
        var sum = 0

        var charSave: Char? = null
        var temp = 0
        for (i in s.indices) {
            sum ++
            if (s[i] == charSave) {
                // continue
                temp ++
                continue
            } else {
                partsCount++
                temp = 1
                charSave = s[i]
            }
        }

        return sum * 1.0 / partsCount
    }
}