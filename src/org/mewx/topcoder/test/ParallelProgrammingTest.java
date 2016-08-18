package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ParallelProgramming;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/18/2016.
 */
public class ParallelProgrammingTest {
    @Test
    public void minTime() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(6517));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    makeCall(parsedResultMeta.get(i).getTestArgs()));
//            System.out.println(TestUtils.getPassedMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    public int makeCall(String src) {
        return new ParallelProgramming().minTime(
                BuiltinParser.parseToIntArray(src.substring(0, src.indexOf("}") + 1)),
                BuiltinParser.parseToStringArray(src.substring(src.lastIndexOf("{"))));
    }

}