import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.GZIPInputStream;

/**
 *  Light Network
 **
 *  This class achieve the basic network protocol:
 *      HttpPost ...
 **/

public class LightNetwork {
    private static CookieManager msCookieManager = new CookieManager();
    private final static int TIMES_TO_SWITCH_IP = 149;
	private static int timeCounter = 0;

    public static void resetCookie() {
//        final String table = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM_";
//
    	msCookieManager = new CookieManager();
//		List<String> cookiesHeader = new ArrayList<>();
//		StringBuilder bid = new StringBuilder("bid=");
//		for (int i = 0; i < 11; i++) bid.append(table.charAt(ThreadLocalRandom.current().nextInt(0, table.length())));
//		bid.append(";");
//		cookiesHeader.add(bid.toString());
//		for (String cookie : cookiesHeader) {
//            msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
//        }
	}

	/**
	 * encodeToHttp:
	 * 
	 * Encode UTF-8 character to http postable style. For example: "妹" =
	 * "%E5%A6%B9"
	 * 
	 * @param str
	 *            : input string
	 * @return result encoded string or empty string
	 */
	public static String encodeToHttp(String str) {
		String enc;
		try {
			enc = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			enc = ""; // prevent crash
		}
		return enc;
	}

	public static void changeIP() {
		try {
			String line;
			Process p = Runtime.getRuntime().exec("python3 /root/programs/douban/Tor_Crawler/src/switchip.py");//.waitFor();
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		timeCounter = 0;
		resetCookie();
	}

	/**
	 * LightHttpDownload:
	 * 
	 * Give direct url to download file in one time, so this only fits small
	 * size files.
	 * 
	 * @param url
	 *            : direct file url with extension
	 * @return return correct bytes or null
	 */
	public static byte[] LightHttpDownload(String url) {
	    // change IP only when fucked
//	    timeCounter ++;
//	    System.out.println(String.format("INFO   next IP progress %d/%d", timeCounter % TIMES_TO_SWITCH_IP, TIMES_TO_SWITCH_IP));
//	    if (timeCounter % TIMES_TO_SWITCH_IP == 0) changeIP();

		InputStream inputStream;
		try {
			URL localURL = new URL(url);
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, InetSocketAddress.createUnresolved("127.0.0.1", 9050));
			HttpURLConnection httpURLConnection = (HttpURLConnection)localURL.openConnection(proxy);
//			HttpURLConnection httpURLConnection = (HttpURLConnection)localURL.openConnection();
			httpURLConnection.setRequestProperty("Referer", url);

			int mv = ThreadLocalRandom.current().nextInt(6, 67);
			int cv = ThreadLocalRandom.current().nextInt(39, 63);
			int saf = ThreadLocalRandom.current().nextInt(537, 578);
			final String ua = "Mozilla/" + mv + ".0 (X11; Linux amd64) AppleWebKit/" + saf + "." + saf + " (KHTML, like Gecko) Chrome/" + cv + ".0." + saf + ".103 Safari/" + saf + ".36";
			httpURLConnection.setRequestProperty("User-Agent", ua);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);

            // set cookie
			if (msCookieManager.getCookieStore().getCookies().size() > 0) {
				// While joining the Cookies, use ',' or ';' as needed. Most of the servers are using ';'
				StringJoiner joiner = new StringJoiner("; ");
				for (HttpCookie c : msCookieManager.getCookieStore().getCookies()) {
					joiner.add(c.toString());
				}
				System.out.println("INFO   " + joiner.toString());
				httpURLConnection.setRequestProperty("Cookie", joiner.toString());
			}

//            boolean redirect = false;
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                inputStream = httpURLConnection.getErrorStream();
//			} else if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP
//					|| httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM
//					|| httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_SEE_OTHER) {
//				redirect = true;
			} else if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            	System.out.println("ERROR: got " + httpURLConnection.getResponseCode() + " in " + url);
				inputStream = httpURLConnection.getErrorStream();
			} else {
                inputStream = httpURLConnection.getInputStream();
            }

//			if (redirect) {
//				String newUrl = httpURLConnection.getHeaderField("Location");
//				String cookies = httpURLConnection.getHeaderField("Set-Cookie");
//
//				// open the new connection again
//				httpURLConnection = (HttpURLConnection) new URL(newUrl).openConnection();
//				httpURLConnection.setRequestProperty("Cookie", cookies);
//				httpURLConnection.addRequestProperty("User-Agent", ua);
//				httpURLConnection.addRequestProperty("Referer", url);
//				System.out.println("INFO  Redirect to URL : " + newUrl);
//
//				if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
//					inputStream = httpURLConnection.getErrorStream();
//				} else if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP
//						|| httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM
//						|| httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_SEE_OTHER) {
//					System.out.println("ERROR: too many re-directions: " + url);
//					inputStream = httpURLConnection.getErrorStream();
//				} else if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//					System.out.println("ERROR: got " + httpURLConnection.getResponseCode() + " in " + url);
//					inputStream = httpURLConnection.getErrorStream();
//				} else {
//					inputStream = httpURLConnection.getInputStream();
//				}
//			}

            // update cookies
			Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
			List<String> cookiesHeader = headerFields.get("Set-Cookie");
			if (cookiesHeader != null) {
				for (String cookie : cookiesHeader) {
					msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
				}
			}

            byte[] b = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if(httpURLConnection.getContentEncoding() != null && httpURLConnection.getContentEncoding().toLowerCase().contains("gzip")) {
                // using 'gzip'
                inputStream = new GZIPInputStream(new BufferedInputStream(inputStream));
            }
            int len;
            while ((len = inputStream.read(b)) != -1) byteArrayOutputStream.write(b, 0, len);
            byteArrayOutputStream.close();

			inputStream.close();
            byteArrayOutputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
}
