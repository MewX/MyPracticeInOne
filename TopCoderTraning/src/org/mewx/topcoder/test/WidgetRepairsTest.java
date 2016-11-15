package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.WidgetRepairs;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/28/2016.
 */
public class WidgetRepairsTest {
    @Test
    public void days() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(1346));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int call(String src) {
        int divider = src.lastIndexOf(",");
        return new WidgetRepairs().days(BuiltinParser.parseToIntArray(src.substring(0, divider)),
                BuiltinParser.parseToInt(src.substring(divider + 1)));
    }
}
