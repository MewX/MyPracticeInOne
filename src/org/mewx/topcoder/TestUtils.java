package org.mewx.topcoder;

/**
 * Created by a1700831 on 12/08/16.
 * This file is for parsing TopCoder Archive files automatically into org.mewx.topcoder.test data.
 */
public class TestUtils {

    public static String getFailureMessage(int failedIdx, int size, String test) {
        return "Failed, passed " + failedIdx + " of " + size + ": \"" + test + "\"";
    }

    public static String getSuccessMessage(int size) {
        return "Succeeded and pased all " + size + " cases.";
    }
}
