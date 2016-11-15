package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ObjectFall;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/28/2016.
 */
public class ObjectFallTest {
    @Test
    public void howLong() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(5915));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int call(String src) {
        int d1 = src.indexOf(",");
        int d2 = src.indexOf(",", d1 + 1);
        return new ObjectFall().howLong(BuiltinParser.parseToInt(src.substring(0, d1)),
                BuiltinParser.parseToInt(src.substring(d1 + 1, d2)),
                BuiltinParser.parseToStringArray(src.substring(d2 + 1)));
    }

}