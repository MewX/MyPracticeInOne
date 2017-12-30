import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by MewX on 05/12/2016.
 * Crawler for 5renti.net
 */
public class Crawl5rt {
    private static int RETRY_TIME = 100;
    private static String DOWNLOADS_FOLDER = "./downloads";
    private static String COVER_FOLDER = DOWNLOADS_FOLDER + File.separator + "covers";

    public static void main(String[] args) throws IOException {
        int begNum = 1489, endNum = 4000;
        if (args.length == 1) {
            begNum = Integer.valueOf(args[0]);
        }

        // build folders
        new File(DOWNLOADS_FOLDER).mkdirs();
        new File(COVER_FOLDER).mkdirs();

        // crawl
        for(int i = begNum; i <= endNum; i ++) {
            System.out.println("Proceeding id: " + i);
            byte[] mainPage = requestUrl(getPageUrl(i, 1));
            if(mainPage == null) continue;

            String mainHtml = new String(mainPage, "UTF-8");
            if (isEmptyPage(mainHtml)) continue;

            // not empty
            // img url, title, total page
            final String contentRegex = "primary.+?<img src=\"(.+?)\".*?alt=\"(.+?)\".+?\\.\\.\\..+?html.*?>(\\d+?)<";
            Matcher contentMatcher = Pattern.compile(contentRegex, Pattern.DOTALL).matcher(mainHtml);
            contentMatcher.find();

            String imgUrl = contentMatcher.group(1);
            String title = contentMatcher.group(2);
            int totalPage = Integer.valueOf(contentMatcher.group(3));
            System.out.println("- Title: " + title + " (" + totalPage + ")");

            String fileName = i + "-" + title;
            byte[] imgResult = requestUrl(imgUrl);
            LightCache.saveFile(COVER_FOLDER + File.separator + fileName + ".jpg", imgResult, true);

            // create zip
            if(LightCache.testFileExist(COVER_FOLDER + File.separator + fileName + ".zip"))
                LightCache.deleteFile(COVER_FOLDER + File.separator + fileName + ".zip"); // delete first
            FileOutputStream fos = new FileOutputStream(DOWNLOADS_FOLDER + File.separator + fileName + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry("1.jpg"));
            zos.write(imgResult, 0, imgResult.length);
            zos.closeEntry();

            // write each page to zip file
            for(int page = 2; page <= totalPage; page ++) {
                String pageHtml = new String(requestUrl(getPageUrl(i, page)), "UTF-8");
                if (isEmptyPage(pageHtml)) continue;

                final String pageRegex = "primary.+?<img src=\"(.+?)\"";
                Matcher pageMatcher = Pattern.compile(pageRegex, Pattern.DOTALL).matcher(pageHtml);
                pageMatcher.find();
                String url = pageMatcher.group(1);

                imgResult = requestUrl(url);
                zos.putNextEntry(new ZipEntry(page + ".jpg"));
                zos.write(imgResult, 0, imgResult.length);
                zos.closeEntry();
            }

            zos.close();
            fos.close();
        }


    }

    private static boolean isEmptyPage(String content) {
        return content == null || content.length() == 0 || content.contains("img404");
    }

    private static String getPageUrl(int id, int page) {
        int header = id / 100;
        if (page == 1)
            return "http://www.5renti.net/article/" + header + "/" + id + ".html";
        else
            return "http://www.5renti.net/article/" + header + "/" + id + "_" + page + ".html";
    }

    private static byte[] requestUrl(String url) {
        byte[] temp = null;
        for(int retry = 0; retry < RETRY_TIME && temp == null; retry ++) {
            temp = LightNetwork.LightHttpDownload(url);
            if(retry != 0)
                System.out.println("- retrying (" + retry + ") : " + url);
        }
        return temp;
    }
}
