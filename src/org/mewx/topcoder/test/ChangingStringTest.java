package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ChangingString;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/31/2016.
 */
public class ChangingStringTest {
    @Test
    public void distance() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(7585));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int call(String src) {
        int idx1 = src.indexOf(",");
        int idx2 = src.indexOf(",", idx1 + 1);
        return new ChangingString().distance(BuiltinParser.parseToString(src.substring(0, idx1)),
                BuiltinParser.parseToString(src.substring(idx1 + 1, idx2)),
                BuiltinParser.parseToInt(src.substring(idx2 + 1)));
    }

}