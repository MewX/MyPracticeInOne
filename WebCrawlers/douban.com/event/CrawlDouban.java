import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlDouban {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static final String BAST_PATH = "/root/programs/dbdata/";
//    private static final String BAST_PATH = "./dbdata/";

    private final String city;
    private final Connection conn;

    public static void main(String[] args) throws ParseException {
        new CrawlDouban("上海").run("https://shanghai.douban.com/events/%s-all?start=%d", 0, dateFormat.parse("20161211"), dateFormat.parse("20171231"));
        new CrawlDouban("深圳").run("https://shenzhen.douban.com/events/%s-all?start=%d", 0, dateFormat.parse("20161211"), dateFormat.parse("20171231"));
        new CrawlDouban("北京").run("https://beijing.douban.com/events/%s-all?start=%d", 0, dateFormat.parse("20161211"), dateFormat.parse("20171231"));
        new CrawlDouban("广州").run("https://guangzhou.douban.com/events/%s-all?start=%d", dateFormat.parse("20161211"), dateFormat.parse("20171231"));
        new CrawlDouban("成都").run("https://www.douban.com/location/chengdu/events/%s-all?start=%d", dateFormat.parse("20161211"), dateFormat.parse("20171231"));
        new CrawlDouban("武汉").run("https://www.douban.com/location/wuhan/events/%s-all?start=%d", dateFormat.parse("20161211"), dateFormat.parse("20171231"));
        new CrawlDouban("杭州").run("https://www.douban.com/location/hangzhou/events/%s-all?start=%d", dateFormat.parse("20161211"), dateFormat.parse("20171231"));
    }

    CrawlDouban(String city) {
        this.city = city;
        this.conn = connect();
    }

    void run(final String baseURL, final Date starting, final Date ending) {
        // baseURL: %s is the date string, %d is the starting page
        run(baseURL, 0, starting, ending);
    }

    void run(final String baseURL, final int startNum, final Date starting, final Date ending) {
        goThroughAllDates(baseURL, startNum, starting, ending);
    }

    private int getTotalPageNumber(String content) {
        Pattern p = Pattern.compile("data-total-page=\"(\\d+?)\"", Pattern.DOTALL);
        Matcher m = p.matcher(content);
        if (m.find()) {
            return Integer.valueOf(m.group(1));
        } else {
            System.out.println("Tried to parse page number, but failed.");
            return -1;
        }
    }

    private void goThroughAllDates(final String base, final int startNum, final Date starting, final Date ending) {
        int realStartNum = startNum;
        Date current = starting;
        while (current != null) {
            System.out.println("INFO entering date " + dateFormat.format(current));
            goThroughAllLists(base, realStartNum, current);

            // next round
            current = getNextDayWithinDeadline(current, ending);
            realStartNum = 0;
        }
    }

    private void goThroughAllLists(final String base, final int startNum, final Date date) {
        int pageTotal = 999999;
        for (int i = startNum; i < pageTotal; i ++) {
            System.out.println("INFO   entering page " + i + "/" + pageTotal + " on " + dateFormat.format(date) + " in " + city);
            File file = new File(city + "/list/" + dateFormat.format(date) + "/" + i + "of" + pageTotal + ".html");
            String listContent = requestUrl(generateListURL(base, date, i * 10), file.getParent(), file.getName());
            if (listContent == null) {
                System.out.println("ERROR: failed to get page: "+ generateListURL(base, date, i * 10));
                break;
            } else if (isEmptyPage(listContent)) {
                System.out.println("WARNING found 404 page: " + generateListURL(base, date, i * 10));
                break;
            }

            // parse each item
            Pattern p = Pattern.compile("<a href=\"https://www.douban.com/event/(\\d+?)/\" .+?<span>(\\d+?)人参加</span> <span class=\"pipe\"></span> <span>(\\d+?)人感兴趣</span>", Pattern.DOTALL);
            Matcher m = p.matcher(listContent);
            while (m.find()) {
                final int dbid = Integer.valueOf(m.group(1));
                final int people_going = Integer.valueOf(m.group(2));
                final int people_interested = Integer.valueOf(m.group(2));

                if (!doesIdExist(dbid)) {
                    parseOnePage(dbid, "https://www.douban.com/event/" + dbid + "/");
                } else {
                    // TODO: update interesting and going people number
                }
            }

            // update page total
            pageTotal = getTotalPageNumber(listContent);
            if (pageTotal <= 0) {
                System.out.println("ERROR in getting: " + generateListURL(base, date, i * 10));
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseOnePage(final int dbid, final String detailURL) {
        System.out.println("INFO     proceeding page: " + detailURL);
        File file = new File(city + "/" + dbid + ".html");
        String content = requestUrl(detailURL, file.getParent(), file.getName());
        if (content == null) {
            System.out.println("ERROR: failed to get page: "+ detailURL);
            return;
        } else if (isEmptyPage(content)) {
            System.out.println("WARNING found 404 page: " + detailURL);
            return;
        }

        // parse content and insert into database
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

            // insert into database
            String insert = "insert into event (dbid, name, time, place, type, provider, people_interested, people_going, info, city, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement st = conn.prepareStatement(insert);
                st.setInt(1, dbid);
                st.setString(2, name);
                st.setString(3, time);
                st.setString(4, place);
                st.setString(5, type);
                st.setString(6, provider);
                st.setInt(7, people_interested);
                st.setInt(8, people_going);
                st.setString(9, info);
                st.setString(10, city);
                st.setString(11, price);
                st.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR Can't parse the page: " + detailURL);
        }

        try {
            // low down the speed
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean doesIdExist(final int doubanId) {
        System.out.println("INFO   validating dbid: " + doubanId);
        final String query = "SELECT count(*) as c FROM event where dbid = " + doubanId;
        int count = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
                count = rs.getInt("c");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't continue, because the database is down.");
            System.exit(-1);
            return false;
        }
        return count == 1;
    }

    private boolean isEmptyPage(String content) {
        Pattern p = Pattern.compile("<title>(.+?)</title>", Pattern.DOTALL);
        Matcher m = p.matcher(content);
        return m.find() && m.group(1).contains("不存在");
    }

    private String generateListURL(final String base, final Date date, final int starting) {
        return String.format(base, dateFormat.format(date), starting);
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/douban?user=root&password=root&character_set_server=utf8mb4");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);
            return null;
        }
    }

    private Date getNextDayWithinDeadline(final Date src, final Date ddl) {
        Calendar c = Calendar.getInstance();
        c.setTime(src);
        c.add(Calendar.DATE, 1);
        Date newDate = c.getTime();

        if (newDate.after(ddl)) return null; // no next day
        else return newDate;
    }

    private String requestUrl(String url, String saveFilePath, String saveFileName) {
        int RETRY_TIME = 10000;
        byte[] temp = null;
        for(int retry = 0; retry < RETRY_TIME && temp == null; retry ++) {
            temp = LightNetwork.LightHttpDownload(url);
            if(retry != 0)
                System.out.println("- retrying (" + retry + ") : " + url);
            if (retry / 20 != 0 && retry % 20 == 0) {
                LightNetwork.changeIP();
            }
        }
        if(temp == null) return null;
        try {
            final String content = new String(temp, "UTF-8");
            if (content.length() < 500) {
                System.out.println("ERROR fucked: " + content);
                System.out.println("INFO changing bid and proxy.");
                LightNetwork.changeIP();
                return requestUrl(url, saveFilePath, saveFileName);
            }

            // save the file
            if (saveFileName != null) {
                LightCache.saveFile(BAST_PATH + saveFilePath, saveFileName, temp, true);
            }

            return content;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
