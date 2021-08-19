package problem.algorithm;

public class S443 {
    public int compress(char[] chars) {
        int origin = 0, update = 0;
        while (origin < chars.length) {
            int len = 1;
            while (origin + len < chars.length && chars[origin + len] == chars[origin]) len ++;

            // keep current character
            chars[update ++] = chars[origin];

            if (len > 1) {
                String strLen = Integer.toString(len);

                for (int i = 0; i < strLen.length(); i ++) {
                    chars[update ++] = strLen.charAt(i);
                }
            }
            origin += len;
        }
        return update;
    }
}
