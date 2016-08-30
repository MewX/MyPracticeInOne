package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.SpecialDay;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/30/2016.
 */
public class SpecialDayTest {
    @Test
    public void howMany() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(7428));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int call(String src) throws ParseException{
        int idx1 = src.indexOf(",");
        int idx2 = src.indexOf(",", idx1 + 1);
        int idx3 = src.indexOf(",", idx2 + 1);
        return new SpecialDay().howMany(BuiltinParser.parseToString(src.substring(0, idx1)),
                BuiltinParser.parseToInt(src.substring(idx1 + 1, idx2)),
                BuiltinParser.parseToString(src.substring(idx2 + 1, idx3)),
                BuiltinParser.parseToInt(src.substring(idx3 + 1)));
    }

}