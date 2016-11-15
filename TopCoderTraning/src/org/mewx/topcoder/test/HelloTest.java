package org.mewx.topcoder.test;

import org.junit.Test;
import org.mewx.topcoder.utils.TestUtils;
import org.mewx.topcoder.problems.Hello;

import static org.junit.Assert.assertEquals;

public class HelloTest {

    @Test
    public void testSay() {
        String[] testCases = new String[] {"", "A", "MewX"};
        String[] answers = new String[]{"Hello", "Hello, A", "Hello, MewX"};

        for (int i = 0; i < testCases.length; i ++) {
            assertEquals(TestUtils.getFailureMessage(i, testCases.length, testCases[i]), new Hello().say(testCases[i]), answers[i]);
        }
        System.out.println(TestUtils.getSuccessMessage(testCases.length));
    }
}
