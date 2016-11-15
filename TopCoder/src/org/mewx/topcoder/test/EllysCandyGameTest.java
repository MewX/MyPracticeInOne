package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.EllysCandyGame;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 9/7/2016.
 */
public class EllysCandyGameTest {
    @Test
    public void getWinner() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(12394));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToString(parsedResultMeta.get(i).getExpectedResult()),
                    new EllysCandyGame().getWinner(BuiltinParser.parseToIntArray(parsedResultMeta.get(i).getTestArgs())));
            System.out.println(TestUtils.getPassedMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }
}
