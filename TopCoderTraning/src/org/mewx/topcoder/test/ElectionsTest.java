package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.Elections;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/31/2016.
 */
public class ElectionsTest {
    @Test
    public void visit() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(4496));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    new Elections().visit(BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getTestArgs())));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}