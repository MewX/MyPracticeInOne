package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.BearPlaysDiv2;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 10/2/2016.
 */
public class BearPlaysDiv2Test {
    @Test
    public void equalPiles() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(13939));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToString(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
            System.out.println(TestUtils.getPassedMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));

    }

    private String call(String src) {
        int idx1 = src.indexOf(",");
        int idx2 = src.lastIndexOf(",");
        return new BearPlaysDiv2().equalPiles(BuiltinParser.parseToInt(src.substring(0, idx1)),
                BuiltinParser.parseToInt(src.substring(idx1 + 1, idx2)),
                BuiltinParser.parseToInt(src.substring(idx2 + 1)));
    }

}