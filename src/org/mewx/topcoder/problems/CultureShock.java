package org.mewx.topcoder.problems;

public class CultureShock {
    public static final int id = 2983;

    public	String translate(String text) {
        final String regex = "\\bZEE\\b";
        return text.replaceAll(regex, "ZED");
    }
}
