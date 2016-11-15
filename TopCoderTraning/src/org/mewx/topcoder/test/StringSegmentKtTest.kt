package org.mewx.topcoder.test

import org.junit.Test
import org.mewx.topcoder.problems.StringSegmentKt
import org.mewx.topcoder.utils.BuiltinParser
import org.mewx.topcoder.utils.TestUtils

import org.junit.Assert.*

/**
 * Created by MewX on 8/30/2016.
 */
class StringSegmentKtTest {
    @Test
    @Throws(Exception::class)
    fun average() {
        val parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(6442))
        for (i in parsedResultMeta.indices) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size, parsedResultMeta[i].testArgs),
                    BuiltinParser.parseToDouble(parsedResultMeta[i].expectedResult),
                    StringSegmentKt().average(BuiltinParser.parseToString(parsedResultMeta[i].testArgs)), 1.0E-9)
        }
        println(TestUtils.getSuccessMessage(parsedResultMeta.size))
    }

}