package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.PartySeats;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/31/2016.
 */
public class PartySeatsTest {
    @Test
    public void seating() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(1854));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertArrayEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getExpectedResult()),
                    new PartySeats().seating(BuiltinParser.parseToStringArray(parsedResultMeta.get(i).getTestArgs())));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

}