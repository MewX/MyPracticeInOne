package org.mewx.topcoder.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a1700831 on 12/08/16.
 */
public class BuiltinParser {

    public static String parseToString(String str) {
        return str.length() <= 2 ? str : str.substring(1, str.length() - 1);
    }

    public static int parseToInt(String str) {
        return Integer.valueOf(str);
    }

    public static double parseToDouble(String str) {
        return Double.valueOf(str);
    }

    public static String[] parseToStringArray(String string) {
        List<String> temp = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            temp.add(matcher.group(1));
        }
        return temp.toArray(new String[0]);
    }
}
