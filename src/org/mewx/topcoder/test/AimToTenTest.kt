package org.mewx.topcoder.test

import org.junit.Test

import org.junit.Assert.*
import org.mewx.topcoder.problems.AimToTen
import org.mewx.topcoder.utils.BuiltinParser
import org.mewx.topcoder.utils.TestUtils

/**
 * Created by MewX on 8/28/2016.
 */
class AimToTenTest {
    @Test
    fun need() {
        val parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(5879))
        for (i in parsedResultMeta.indices) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size, parsedResultMeta[i].testArgs),
                    BuiltinParser.parseToInt(parsedResultMeta[i].expectedResult),
                    AimToTen().need(BuiltinParser.parseToIntArray(parsedResultMeta[i].testArgs)))
        }
        println(TestUtils.getSuccessMessage(parsedResultMeta.size))
    }
}