package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.Fragile2;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mewx on 20/10/16.
 */
public class Fragile2Test {
    @Test
    public void countPairs() throws Exception {
        List<ParsedResultMeta> parsed = TestUtils.parseTestData(TestUtils.getFileContentById(13648));
        for (int i = 0; i < parsed.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsed.size(), parsed.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsed.get(i).getExpectedResult()),
                    new Fragile2().countPairs(BuiltinParser.parseToStringArray(parsed.get(i).getTestArgs())));
            System.out.println(TestUtils.getPassedMessage(i, parsed.size(), parsed.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsed.size()));
    }

}