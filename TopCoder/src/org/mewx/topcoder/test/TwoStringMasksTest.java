package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.TwoStringMasks;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/17/2016.
 */
public class TwoStringMasksTest {
    @Test
    public void shortestCommon() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(8706));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToString(parsedResultMeta.get(i).getExpectedResult()),
                    makeCall(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private String makeCall(String src) {
        int idx = src.indexOf(",");
        return new TwoStringMasks().shortestCommon(BuiltinParser.parseToString(src.substring(0, idx)),
                BuiltinParser.parseToString(src.substring(idx + 1)));
    }
}