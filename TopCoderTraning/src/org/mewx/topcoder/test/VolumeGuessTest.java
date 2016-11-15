package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.problems.VolumeGuess;
import org.mewx.topcoder.utils.BuiltinParser;
import org.mewx.topcoder.utils.ParsedResultMeta;
import org.mewx.topcoder.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MewX on 8/31/2016.
 */
public class VolumeGuessTest {
    @Test
    public void correctVolume() throws Exception {
        List<ParsedResultMeta> parsedResultMeta = TestUtils.parseTestData(TestUtils.getFileContentById(2330));
        for (int i = 0; i < parsedResultMeta.size(); i ++) {
            assertEquals(TestUtils.getFailureMessage(i, parsedResultMeta.size(), parsedResultMeta.get(i).getTestArgs()),
                    BuiltinParser.parseToInt(parsedResultMeta.get(i).getExpectedResult()),
                    call(parsedResultMeta.get(i).getTestArgs()));
        }
        System.out.println(TestUtils.getSuccessMessage(parsedResultMeta.size()));
    }

    private int call(String src) {
        int idx2 = src.lastIndexOf(",");
        int idx1 = src.lastIndexOf(",", idx2 - 1);
        return new VolumeGuess().correctVolume(BuiltinParser.parseToStringArray(src.substring(0, idx1)),
                BuiltinParser.parseToInt(src.substring(idx1 + 1, idx2)),
                BuiltinParser.parseToInt(src.substring((idx2 + 1))));
    }

}