package org.mewx.topcoder.utils;

import com.sun.istack.internal.NotNull;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a1700831 on 12/08/16.
 * This file is for parsing TopCoder Archive files automatically into org.mewx.topcoder.test data.
 */
public class TestUtils {

    @NotNull
    public static String getFailureMessage(int failedIdx, int size, @NotNull String test) {
        return "Failed, passed " + failedIdx + " of " + size + ": \"" + test + "\"";
    }

    @NotNull
    public static String getPassedMessage(int passIdx, int size, @NotNull String test) {
        return "Passed " + passIdx + " of " + size + ": \"" + test + "\"";
    }

    @NotNull
    public static String getSuccessMessage(int size) {
        return "Succeeded and passed all " + size + " cases.";
    }

    @NotNull
    public static String getTestFilePath(int id) {
        return "testdata/" + id + ".html";
    }

    @NotNull
    public static String getFileContentById(int id) {
        try {
            return new String(IOUtils.readFully(new FileInputStream(new File(getTestFilePath(id))), -1, true), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    @NotNull
    public static List<ParsedResultMeta> parseTestData(@NotNull String data) {
        final String regex = "CLASS=\"statText\" ALIGN=\"left\">(.*?)</TD.*?ALIGN=\"right\">(.*?)</TD";
        List<ParsedResultMeta> listParsedResultMeta = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            ParsedResultMeta temp = new ParsedResultMeta(matcher.group(1).trim(), matcher.group(2).trim());
            listParsedResultMeta.add(temp);
//            System.out.println(temp.getTestArgs() + temp.getExpectedResult());
        }
        return listParsedResultMeta;
    }


}
