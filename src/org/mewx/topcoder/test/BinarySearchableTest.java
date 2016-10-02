package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.BinarySearchable;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 10/2/2016.
 */
public class BinarySearchableTest {
    @Test
    public void howMany() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(2292));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertArrayEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToIntArray(parsedResultMeta.get(i).getExpectedResult()),
                    new BinarySearchable().fiboCallsMade(BuiltinParser.parseToInt(parsedResultMeta.get(i).getTestArgs())));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}