package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ElevatorLimit;
import org.mewx.topcoder.problems.GravityBomb;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/16/2016.
 */
public class ElevatorLimitTest {
    @Test
    public void getRange() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(1965));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertArrayEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToIntArray(parsedResultMeta.get(i).getExpectedResult()),
                    makeCall(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int[] makeCall(String args) {
        int end1 = args.indexOf("}");
        int beg2 = args.lastIndexOf("{"), end2 = args.lastIndexOf("}");

        return new ElevatorLimit().getRange(BuiltinParser.parseToIntArray(args.substring(0, end1 + 1)),
                BuiltinParser.parseToIntArray(args.substring(beg2, end2 + 1)),
                BuiltinParser.parseToInt(args.substring(args.lastIndexOf(",") + 1)));
    }

}