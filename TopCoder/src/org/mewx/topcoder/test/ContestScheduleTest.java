package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ContestSchedule;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/18/2016.
 */
public class ContestScheduleTest {
    @Test
    public void expectedWinnings() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(6708));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())),
                    BuiltinParser.parseToDouble(parsedResultMeta.get(i).getExpectedResult()),
                    new ContestSchedule().expectedWinnings(BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getTestArgs())), 1.0e-9);
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}