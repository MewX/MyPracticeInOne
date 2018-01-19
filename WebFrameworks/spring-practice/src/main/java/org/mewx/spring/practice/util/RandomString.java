package org.mewx.spring.practice.util;

import java.security.SecureRandom;

public class RandomString {
    private static SecureRandom random = new SecureRandom();

    public static String numbersOnly(final int length) {
        final String table = "0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(table.charAt(random.nextInt(table.length())));
        return sb.toString();
    }

    /**
     * Token string without I, O, 1, 0
     * @param length the length of target string
     * @return the token string
     */
    public static String tokenString(final int length) {
        final String table = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(table.charAt(random.nextInt(table.length())));
        return sb.toString();
    }
}
