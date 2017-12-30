import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadedParser {
    public static void main(String[] args) throws IOException {
        final String BASE_PATH = "/home/cloud-user/dbdata";
        final String[] CITY_LIST = {"上海", "北京", "广州", "成都", "杭州", "武汉", "深圳"};

        int breakCounter = 1000;
        FileWriter writer = new FileWriter(BASE_PATH + "test.csv"); // override
        LightCsv.writeLine(writer, new String[] {"dbid", "name", "time", "place", "type", "provider", "people_interested", "people_going", "info", "city", "price"});
        for (String city : CITY_LIST) {
            final String PATH = BASE_PATH + city + "/";
            File folder = new File(PATH);
            for (File listOfFile : folder.listFiles()) {
                // do with file only
                if (listOfFile.isFile()) {
                    System.out.println("File: " + listOfFile.getAbsolutePath());
                    LightCsv.writeLine(writer, parseFile(city, listOfFile.getName().replaceFirst("[.][^.]+$", ""), LightCache.loadFile(listOfFile.getAbsolutePath())));

                    breakCounter--;
                    if (breakCounter <= 0) break;
                }
            }
        }

        writer.flush();
        writer.close();
    }

    private static String[] parseFile(final String city, final String id, final byte[] raw) throws UnsupportedEncodingException {
        String content = new String(raw, "UTF-8");
        // TODO: test charset
        final String regchar = "<meta.+?charset=(.+?)[\\\" ]";
        Pattern pchar = Pattern.compile(regchar);
        Matcher mchar = pchar.matcher(content);
        if (mchar.find()) {
            final String charset = mchar.group(1).toLowerCase();
            if (!charset.equals("utf-8")) {
                System.out.println("Unknown charset: " + charset);
                content = new String(raw, charset);
            }
        } else {
            System.out.println("ERROR Can't find charset in: " + id + ".html");
            System.exit(-1);
        }

        content = content.replace("&nbsp;", " ");
        final String regex = "<h1 itemprop=\"summary\">(.+?)<.+?calendar-strs.+?li.*?>(.+?)<.+?<time itemprop=\"startDate\" datetime=\"(.+?)\"></time>.+?<time itemprop=\"endDate\" datetime=\"(.+?)\"></time>.+?street-address\" class=\"micro-address\">(.+?)</span>.+?>费用:(.+?)</span>.+?类型:.+?<a.+?>(.+?)</a>.+?(者|方):.+?href=\"(.+?)\".+?>(.+?)<.+?<span class=\"num\">(.+?)</span><span>人感兴趣.+?<span class=\"num\">(.+?)</span><span>人要参加.+?class=\"wr\">(.+?)</div";
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher m = p.matcher(content);
        if (m.find()) {
            final String name = m.group(1).trim();
            final String time = String.format("%s (%s -> %s)", m.group(2).trim(), m.group(3).trim(), m.group(4).trim());
            final String place = String.format("%s, %s", city, m.group(5).trim());
            final String price = m.group(6).trim();
            final String type = m.group(7).trim();
            final String provider = String.format("%s (%s)", m.group(10).trim(), m.group(9).trim());
            final int people_interested = Integer.valueOf(m.group(11).trim());
            final int people_going = Integer.valueOf(m.group(12).trim());
            final String info = m.group(13).trim();
            return new String[] {id, name, time, place, type, provider, "" + people_interested, "" + people_going, info, city, price};
        } else {
            System.out.println("ERROR Can't parse the: " + id + ".html");
            System.exit(-1);
            return null;
        }
    }
}
