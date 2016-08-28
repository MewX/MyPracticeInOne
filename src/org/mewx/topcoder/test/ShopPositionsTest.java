package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ShopPositions;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/26/2016.
 */
public class ShopPositionsTest {
    @Test
    public void maxProfit() throws Exception {

        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(ShopPositions.id));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    makeCall(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    public int makeCall(String src) {
        int first = src.indexOf(",");
        int second = src.indexOf(",", first + 1);
        return new ShopPositions().maxProfit(BuiltinParser.parseToInt(src.substring(0, first)),
                BuiltinParser.parseToInt(src.substring(first + 1, second)),
                BuiltinParser.parseToIntArray(src.substring(second + 1)));
    }
}