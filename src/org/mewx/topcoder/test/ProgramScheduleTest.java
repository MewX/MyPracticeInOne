package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.ProgramSchedule;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 9/17/2016.
 */
public class ProgramScheduleTest {
    @Test
    public void schedule() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(3945));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int call(String src) {
        return new ProgramSchedule().schedule(BuiltinParser.parseToIntArray(src.substring(0, src.indexOf("}") + 1)),
                BuiltinParser.parseToIntArray(src.substring(src.lastIndexOf("{"))));
    }

}