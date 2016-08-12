package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 12/08/16.
 */
public class Hello {
    public String say(String name) {
        return name == null || name.length() == 0 ? "Hello" : "Hello, " + name;
    }
}
