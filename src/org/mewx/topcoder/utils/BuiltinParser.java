package org.mewx.topcoder.utils;

/**
 * Created by a1700831 on 12/08/16.
 */
public class BuiltinParser {

    public static String parseToString(String str) {
        return str.length() <= 2 ? str : str.substring(1, str.length() - 1);
    }
}
