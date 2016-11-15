package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.AlternateColors;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/28/2016.
 */
public class AlternateColorsTest {
    @Test
    public void ballColor() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(12343));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToString(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private String call(String src) {
        int d1 = src.indexOf(",");
        int d2 = src.indexOf(",", d1 + 1);
        int d3 = src.indexOf(",", d2 + 1);
        return new AlternateColors().ballColor(BuiltinParser.parseToLong(src.substring(0, d1)),
                BuiltinParser.parseToLong(src.substring(d1 + 1, d2)),
                BuiltinParser.parseToLong(src.substring(d2 + 1, d3)),
                BuiltinParser.parseToLong(src.substring(d3 + 1)));
    }

}