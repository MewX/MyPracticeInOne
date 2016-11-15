package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.CultureShock;
import org.mewx.topcoder.problems.ProblemsToSolve;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/16/2016.
 */
public class ProblemsToSolveTest {
    @Test
    public void minNumber() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(7504));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    getCall(parsedResultMeta.get(i).getTestArgs()));
//            System.out.print(TestUtils.getPassedMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int getCall(String src) {
        int i = src.lastIndexOf(',');
        return new ProblemsToSolve().minNumber(BuiltinParser.parseToIntArray(src.substring(0, i)), BuiltinParser.parseToInt(src.substring(i + 1)));
    }

}