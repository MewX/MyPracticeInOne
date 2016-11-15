package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.StringSegment;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/28/2016.
 */
public class StringSegmentTest {
    @Test
    public void average() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(6442));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToDouble(parsedResultMeta.get(i).getExpectedResult()),
                    new StringSegment().average(BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())), 1.0E-9);
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}