import com.sun.istack.internal.Nullable;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by MewX on 05/12/2016.
 * Crawler for xmeise.com
 */
public class CrawlXmeise {
    private static int RETRY_TIME = 100;
    private static String DOWNLOADS_FOLDER = "./xmeise_dl";

    private static String[] subDomainList = {
            "bijini/neiyi/",
            "bijini/neiku/",
            "bijini/dingziku/",
            "zhifu/xuesheng/",
            "zhifu/nvpu/",
            "zhifu/ol/",
            "zhifu/kongjie/",
            "zhifu/junjing/",
            "zhifu/kunbang/",
            "zhifu/hushi/",
            "zhifu/tunvlang/",
            "nvyou/riben/",
            "nvyou/oumei/",
            "nvyou/guonei/",
            "nvyou/hanguo/",
            "nvyou/eluosi/",
            "nvyou/luoti/",
            "xingge/qingchun/",
            "xingge/weimeitu/",
            "xingge/shunv/",
            "xingge/piaoliang/",
            "xingge/fengman/",
            "xingge/fengsao/",
            "xingge/youhuo/",
            "xingge/xinggan/",
            "rufang/meixiong/",
            "rufang/xinggan/",
            "rufang/tu/",
            "meitun/qiaotun/",
            "meitun/feitun/",
            "meitun/tunbu/",
            "meitun/pigu/",
            "av/qt/",
            "av/syxl/",
            "av/xqa/",
            "av/cs/",
            "av/jcmb/",
            "av/thy/",
            "av/bmyj/",
            "av/ctxl/",
            "av/xzmly/",
            "av/tyhlx/",
            "av/cxs/",
            "av/xc/",
            "av/tp/",
            "av/cangjingkong/",
            "xinggan/hanguo/",
            "xinggan/riben/",
            "xinggan/eluosi/",
            "xinggan/taiguo/",
            "xinggan/hunxue/",
            "xinggan/zhongguo/",
            "mote/zhongguo/",
            "mote/eluosi/",
            "mote/oumei/",
            "mote/hanguo/",
            "mote/riben/",
            "mote/chemo/",
            "siwa/zhifu/",
            "siwa/meitui/",
            "siwa/shaofu/",
            "siwa/shunv/",
            "siwa/heisi/",
            "qunzhuang/shuiqun/",
            "qunzhuang/lifu/",
            "qunzhuang/duanqun/",
            "qunzhuang/diaodai/",
            "qunzhuang/qipao/",
            "meinv/jiepai/",
            "meinv/mndtt/",
            "meinv/heiren/",
            "meinv/duanfa/",
            "meinv/tuoyi/",
            "meinv/luotu/",
            "meinv/jinshenku/",
            "meinv/niuzai/",
            "meinv/guzhuang/",
            "meinv/kbmn/",
            "qingqu/aiaitu/",
            "qingqu/rewu/",
            "qingqu/youxi/",
            "qingqu/ziwei/"
    };

    private static String baseFolder;
    private static String baseUrl;

    public static void main(String[] args) throws IOException {
        int begIdx = 0, endIdx = subDomainList.length - 1;
        if (args.length == 1) {
            for(int i = 0; i < endIdx; i ++) {
                if(subDomainList[i].contains(args[0])) {
                    begIdx = i;
                    break;
                }
            }
        }

        for (int idx = begIdx; idx <= endIdx; idx ++) {
            String currentFolder = subDomainList[idx];

            baseFolder = DOWNLOADS_FOLDER + File.separator + currentFolder;
            baseUrl = "http://mm.xmeise.com/" + currentFolder;
            System.out.println("Proceeding: " + baseUrl);
            new File(baseFolder).mkdirs();

            String mainList = new String(requestUrl(baseUrl), "GB2312");
            final String pageRegex = "\"pages\".+?list_(\\d+?)_.+?(\\d+?).html'>末页";
            Matcher pageMatcher = Pattern.compile(pageRegex, Pattern.DOTALL).matcher(mainList);

            if(!pageMatcher.find()) {
                saveImages(mainList, 1);
                continue;
            }

            int listNumber = Integer.valueOf(pageMatcher.group(1));
            int totalPage = Integer.valueOf(pageMatcher.group(2));

            for(int i = 1; i <= totalPage; i ++) {
                String listPageUrl = baseUrl + "list_" + listNumber + "_" + i + ".html";
                System.out.println("Proceeding: " + listPageUrl);
                String mainPage = new String(requestUrl(listPageUrl), "GB2312");

                saveImages(mainPage, totalPage);
            }

        }
    }

    private static void saveImages(String content, int totalPage) throws IOException {
        // cover img url, title, id
        final String imgRegex = "data-original=\"(.+?)\".*?\"(.*?)\".+?/(\\d.+?)\\.h";
        Matcher imgMatcher = Pattern.compile(imgRegex, Pattern.DOTALL).matcher(content);
        while (imgMatcher.find()) {
            String coverUrl = imgMatcher.group(1);
            String title = imgMatcher.group(2);
            String id = imgMatcher.group(3);
            System.out.println("- Title: " + title + " (" + totalPage + ")");

            // save cover
            String fileName = id + "-" + title;
            byte[] imgResult = requestUrl(coverUrl);
            LightCache.saveFile(baseFolder + fileName + ".jpg", imgResult, true);

            // make zip file
            if(LightCache.testFileExist(baseFolder + fileName + ".zip"))
                LightCache.deleteFile(baseFolder + fileName + ".zip"); // delete first
            FileOutputStream fos = new FileOutputStream(baseFolder + fileName + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            // request the article
            String articleMain = new String(requestUrl(baseUrl + id + ".html"), "GB2312");
            final String imgMainRegex = "img-wrap.+?img.+?\"(.+?)\""; // need to judge contains "http"
            Matcher imgMainMatcher = Pattern.compile(imgMainRegex, Pattern.DOTALL).matcher(articleMain);

            int temp = 1;
            while (imgMainMatcher.find()) {
                String url = imgMainMatcher.group(1);
                if(!url.contains("http")) continue;

                // save image to zip
                imgResult = requestUrl(url);
                zos.putNextEntry(new ZipEntry(temp + ".jpg"));
                zos.write(imgResult, 0, imgResult.length);
                zos.closeEntry();
                temp ++;
            }

            zos.close();
            fos.close();
        }
    }

    @Nullable
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
