package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.GravityBomb;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class GravityBombTest {
    @Test
    public void aftermath() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(GravityBomb.id));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertArrayEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())),
                    BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getExpectedResult()),
                    new GravityBomb().aftermath(BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getTestArgs())));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }
}