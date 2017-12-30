import com.sun.istack.internal.Nullable;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

/**
 *  Light Network
 **
 *  This class achieve the basic network protocol:
 *      HttpPost ...
 **/

public class LightNetwork {
//    final static private String fromEle = ".cn/";
//    final static private String toEle = ".com/";

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
	@Nullable
	public static byte[] LightHttpDownload(String url) {

		InputStream inputStream;
		try {
			URL localURL = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)localURL.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);

//            boolean redirect = false;
//            // normally, 3xx is redirect
//            int status = httpURLConnection.getResponseCode();
//            if (status != HttpURLConnection.HTTP_OK) {
//                if (status == HttpURLConnection.HTTP_MOVED_TEMP
//                        || status == HttpURLConnection.HTTP_MOVED_PERM
//                        || status == HttpURLConnection.HTTP_SEE_OTHER)
//                    redirect = true;
//            }
//
//            if (redirect) {
//                // get redirect url from "location" header field
//                String newUrl = httpURLConnection.getHeaderField("Location");
//
//                // get the cookie if need, for login
//                String cookies = httpURLConnection.getHeaderField("Set-Cookie");
//
//                // open the new connnection again
//                httpURLConnection = (HttpURLConnection) new URL(newUrl).openConnection();
//                httpURLConnection.setRequestProperty("Cookie", cookies);
//                httpURLConnection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
//                httpURLConnection.addRequestProperty("User-Agent", "Mozilla");
//                httpURLConnection.addRequestProperty("Referer", "google.com");
//            }

//			if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
//				throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                inputStream = httpURLConnection.getErrorStream();
            } else {
                inputStream = httpURLConnection.getInputStream();
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
