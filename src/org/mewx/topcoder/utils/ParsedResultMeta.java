package org.mewx.topcoder.utils;

public class ParsedResultMeta {
    private String testArgs;
    private String expectedResult;

    public ParsedResultMeta() {
        testArgs = "";
        expectedResult = "";
    }

    public ParsedResultMeta(String args, String results) {
        testArgs = args;
        expectedResult = results;
    }

    public String getTestArgs() {
        return testArgs;
    }

    public void setTestArgs(String testArgs) {
        this.testArgs = testArgs;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
}
