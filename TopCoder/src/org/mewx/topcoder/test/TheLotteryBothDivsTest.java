package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.TheLotteryBothDivs;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/12/2016.
 */
public class TheLotteryBothDivsTest {
    private final double DELTA = 1e-9;

    @Test
    public void find() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(TheLotteryBothDivs.id));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())),
                    BuiltinParser.parseToDouble(parsedResultMeta.get(i).getExpectedResult()),
                    new TheLotteryBothDivs().find(BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getTestArgs())), DELTA);
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }
}
