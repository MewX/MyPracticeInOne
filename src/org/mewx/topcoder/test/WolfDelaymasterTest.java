package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.WolfDelaymaster;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class WolfDelaymasterTest {
    @Test
    public void check() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(12778));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())),
                    new WolfDelaymaster().check(BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())),
                    BuiltinParser.parseToString(parsedResultMeta.get(i).getExpectedResult()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}