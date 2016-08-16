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
        return Integer.valueOf(str.trim());
    }

    public static double parseToDouble(String str) {
        return Double.valueOf(str.trim());
    }

    public static String[] parseToStringArray(String string) {
        // {"1", "2", "3"}
        List<String> temp = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            temp.add(matcher.group(1));
        }
        return temp.toArray(new String[temp.size()]);
    }

    public static int[] parseToIntArray(String string) {
        // {1, 2, 3}
        String[] temp = string.substring(string.indexOf("{") + 1, string.lastIndexOf("}")).split(",");
        if (temp.length == 0 || temp.length == 1 && temp[0].trim().equals("")) return new int[0];

        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i ++) {
            result[i] = Integer.valueOf(temp[i].trim());
        }
        return result;
    }
}
