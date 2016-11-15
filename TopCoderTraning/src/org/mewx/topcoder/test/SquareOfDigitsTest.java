package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.CultureShock;
import org.mewx.topcoder.problems.SquareOfDigits;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/16/2016.
 */
public class SquareOfDigitsTest {
    @Test
    public void getMax() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(10395));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), BuiltinParser.parseToString(parsedResultMeta.get(i).getTestArgs())),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    new SquareOfDigits().getMax(BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getTestArgs())));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}