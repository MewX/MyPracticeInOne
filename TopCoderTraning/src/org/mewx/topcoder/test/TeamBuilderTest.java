package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.TeamBuilder;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class TeamBuilderTest {
    @Test
    public void specialLocations() throws Exception {
        List<ParsedResultMeta> parsed = TestUtils.parseTestData(TestUtils.getFileContentById(2356));
        for (int i = 0; i < parsed.size(); i ++) {
            assertArrayEquals(TestUtils.getFailureMessage(i, parsed.size(), parsed.get(i).getTestArgs()),
                    BuiltinParser.parseToIntArray(parsed.get(i).getExpectedResult()),
                    new TeamBuilder().specialLocations(BuiltinParser.parseToStringArray(parsed.get(i).getTestArgs())));
            System.out.println(TestUtils.getPassedMessage(i, parsed.size(), parsed.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsed.size()));
    }

}