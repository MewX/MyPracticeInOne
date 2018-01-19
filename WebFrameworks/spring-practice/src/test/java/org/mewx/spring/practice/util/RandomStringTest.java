package org.mewx.spring.practice.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomStringTest {
    @Test
    public void numbersOnly() throws Exception {
        assertNotEquals(RandomString.numbersOnly(3), RandomString.numbersOnly(3));
        assertNotEquals(RandomString.numbersOnly(6), RandomString.numbersOnly(6));
    }

    @Test
    public void tokenString() throws Exception {
        assertNotEquals(RandomString.tokenString(5), RandomString.tokenString(5));
    }

}